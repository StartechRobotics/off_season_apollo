package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Chassis extends SubsystemBase {
  TalonSRX tmotor_chasis_fl;
  TalonSRX tmotor_chasis_fr;
  TalonSRX tmotor_chasis_bl;
  TalonSRX tmotor_chasis_br;

  ADXRS450_Gyro gyro = new ADXRS450_Gyro();

  Encoder encoder;

  double leftPower;
  double rightPower;

  double left_output;
  double right_output;

  double error; 
  double init;
  double setpoint;
  double distance;
  double power= 0;

  double Kp;

  public Chassis() {
  //Aquí se inicializan las acciónes que solo se ejecutan una vez. (inverted, encoderPos, reset, etc).
    tmotor_chasis_fl = new TalonSRX(Constants.TMOTOR_CHASSIS_FL);
    tmotor_chasis_fr = new TalonSRX(Constants.TMOTOR_CHASSIS_FR);
    tmotor_chasis_bl = new TalonSRX(Constants.TMOTOR_CHASSIS_BL);
    tmotor_chasis_br = new TalonSRX(Constants.TMOTOR_CHASSIS_BR);

    tmotor_chasis_fl.configPeakCurrentLimit(Constants.k_Currentlimit_CIM);
    tmotor_chasis_fr.configPeakCurrentLimit(Constants.k_Currentlimit_CIM);
    tmotor_chasis_bl.configPeakCurrentLimit(Constants.k_Currentlimit_CIM);
    tmotor_chasis_br.configPeakCurrentLimit(Constants.k_Currentlimit_CIM);
   
    // tmotor_chasis_fl.configOpenloopRamp(Constants.k_timeToFull_ms);
    

    tmotor_chasis_bl.setInverted(true);
    tmotor_chasis_fl.setInverted(true);

    // tmotor_chasis_bl.setNeutralMode(NeutralMode.Brake);
    // tmotor_chasis_fl.setNeutralMode(NeutralMode.Brake);
    // tmotor_chasis_br.setNeutralMode(NeutralMode.Brake);
    // tmotor_chasis_fr.setNeutralMode(NeutralMode.Brake);

    // encoder = new Encoder(2,3, true);
    // encoder.setDistancePerPulse((6*Math.PI)/128);

    // encoder.reset();
    gyro.reset();

    Kp = 0.5;
  }
  //Comando de defecto, "default command". comando para manejo del robot, se lee constantemente cada iteracion.
  public void mainDrive(double drive, double turn){
    leftPower = drive - turn;
    rightPower = drive + turn;

    tmotor_chasis_bl.set(ControlMode.PercentOutput, leftPower);
    tmotor_chasis_fl.set(ControlMode.PercentOutput, leftPower);

    tmotor_chasis_br.set(ControlMode.PercentOutput, rightPower);
    tmotor_chasis_fr.set(ControlMode.PercentOutput, rightPower);
  }

  public void moveBackwards(){
  
    tmotor_chasis_bl.set(ControlMode.PercentOutput, 0.5);
    tmotor_chasis_fl.set(ControlMode.PercentOutput, 0.5);

    tmotor_chasis_br.set(ControlMode.PercentOutput, 0.5);
    tmotor_chasis_fr.set(ControlMode.PercentOutput, 0.5);
  }

  public void moveForward(){
  
    tmotor_chasis_bl.set(ControlMode.PercentOutput, -0.5);
    tmotor_chasis_fl.set(ControlMode.PercentOutput, -0.5);

    tmotor_chasis_br.set(ControlMode.PercentOutput, -0.5);
    tmotor_chasis_fr.set(ControlMode.PercentOutput, -0.5);
  }

  // public void AutoMove(double lerror, double rerror){
  //   left_output = lerror * 0.1;
  //   right_output = rerror * 0.1;

  //   tmotor_chasis_bl.set(ControlMode.PercentOutput, left_output);
  //   tmotor_chasis_fl.set(ControlMode.PercentOutput, left_output);

  //   tmotor_chasis_br.set(ControlMode.PercentOutput, right_output);
  //   tmotor_chasis_fr.set(ControlMode.PercentOutput, right_output);
   
  // }

  public void stopChassis(){
    tmotor_chasis_bl.set(ControlMode.PercentOutput, 0);
    tmotor_chasis_fl.set(ControlMode.PercentOutput, 0);

    tmotor_chasis_br.set(ControlMode.PercentOutput, 0);
    tmotor_chasis_fr.set(ControlMode.PercentOutput, 0);
    power =0;
  }

  // public double getEncoder(){
  //   distance = encoder.getDistance();
  //   return distance;
  // }

  public double getGyro(){
    return gyro.getAngle();
  }

  public void turn(double init, double angle, PIDController pid_object){
    setpoint = init + angle;
    double error = setpoint - gyro.getAngle();
    power = pid_object.calculate(setpoint, error);

    tmotor_chasis_bl.set(ControlMode.PercentOutput, -power);
    tmotor_chasis_fl.set(ControlMode.PercentOutput, -power);
    tmotor_chasis_br.set(ControlMode.PercentOutput, power);
    tmotor_chasis_fr.set(ControlMode.PercentOutput, power);

    SmartDashboard.putNumber("gyro", gyro.getAngle());
    SmartDashboard.putNumber("init", init);
    SmartDashboard.putNumber("error", error);
    SmartDashboard.putNumber("power", power);

    
  }

  //Metodos que se usan en los comandos.
  @Override
  public void periodic() {
    // SmartDashboard.putNumber("Rotations", encoder.getDistance());

    SmartDashboard.putNumber("bl", tmotor_chasis_bl.getMotorOutputPercent());

    SmartDashboard.putNumber("fl", tmotor_chasis_fl.getMotorOutputPercent());

    SmartDashboard.putNumber("br", tmotor_chasis_br.getMotorOutputPercent());
    
    SmartDashboard.putNumber("fr", tmotor_chasis_fr.getMotorOutputPercent());


  }
}
