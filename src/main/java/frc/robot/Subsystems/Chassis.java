package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Chassis extends SubsystemBase {
//Aquí se declaran las cosas de hardware, (controladores, sensores, commandgroups)
  TalonSRX tmotor_chasis_fl = new TalonSRX(Constants.TMOTOR_CHASSIS_FL);
  TalonSRX tmotor_chasis_fr = new TalonSRX(Constants.TMOTOR_CHASSIS_FR);
  TalonSRX tmotor_chasis_bl = new TalonSRX(Constants.TMOTOR_CHASSIS_BL);
  TalonSRX tmotor_chasis_br = new TalonSRX(Constants.TMOTOR_CHASSIS_BR);
 
  ADXRS450_Gyro gyro = new ADXRS450_Gyro();

  double leftPower;
  double rightPower;

  public Chassis() {
  //Aquí se inicializan las acciónes que solo se ejecutan una vez. (inverted, encoderPos, reset, etc).
    tmotor_chasis_bl.setInverted(true);
    tmotor_chasis_fl.setInverted(true);

    gyro.reset();
  }

  //Aquí se ponen las acciones que se repiten periodicamente
  @Override
  public void periodic() {}

  public void mainDrive(double drive, double turn){
    leftPower = drive - turn;
    rightPower = drive + turn;

    tmotor_chasis_bl.set(ControlMode.PercentOutput, leftPower);
    tmotor_chasis_fl.set(ControlMode.PercentOutput, leftPower);
    tmotor_chasis_br.set(ControlMode.PercentOutput, rightPower);
    tmotor_chasis_fr.set(ControlMode.PercentOutput, rightPower);
  }


}
