// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import java.nio.file.LinkPermission;

import org.ejml.equation.IntegerSequence.Range;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants;

public class Turret extends SubsystemBase {
  CANSparkMax motor_turret_l;
  CANSparkMax motor_turret_r;

  // Limit switches - comentado hasta instalarlos
  DigitalInput lswitch_front;

  RelativeEncoder encoder_l;
  RelativeEncoder encoder_r;

  SparkPIDController l_PID;  
  SparkPIDController r_PID;


  double position;
  double default_power;
  double power;
  double rotations;

  double kP; 
  double kI;
  double kD; 
  double kIz; 
  double kFF; 
  double kMaxOutput; 
  double kMinOutput;

  // Inicializador de clase ---------------
  public Turret() {
    //inicializar motores
    motor_turret_l = new CANSparkMax(Constants.SMOTOR_SHOOTER_L, MotorType.kBrushless);
    motor_turret_r = new CANSparkMax(Constants.SMOTOR_SHOOTER_R, MotorType.kBrushless);

    motor_turret_l.restoreFactoryDefaults();
    motor_turret_r.restoreFactoryDefaults();

    motor_turret_l.setInverted(true);
    motor_turret_r.setInverted(false);

    motor_turret_l.setIdleMode(IdleMode.kBrake);
    motor_turret_r.setIdleMode(IdleMode.kBrake);

    //inicializar encoders
    encoder_l = motor_turret_l.getEncoder();
    encoder_r = motor_turret_r.getEncoder();

    encoder_l.setPositionConversionFactor(7.71);
    encoder_r.setPositionConversionFactor(7.71);

    //inicializar limitswitches
    lswitch_front = new DigitalInput(Constants.LSWITCH_FWD);
    if (lswitch_front.get() == false){
      resetEncoders();
    }

    //inicializar PID
    l_PID = motor_turret_l.getPIDController();
    r_PID = motor_turret_r.getPIDController();

    //constants
    double kP = 0.01; 
    double kI = .00;
    double kD = 0.6; 
    double kIz = 0; 
    double kFF = 0; 
    double kMaxOutput = 0.2; 
    double kMinOutput = -0.2;

    //PID constants
    r_PID.setP(kP);
    r_PID.setI(kI);
    r_PID.setD(kD);
    r_PID.setIZone(kIz);
    r_PID.setFF(kFF);
    r_PID.setOutputRange(kMinOutput, kMaxOutput);

    l_PID.setP(kP);
    l_PID.setI(kI);
    l_PID.setD(kD);
    l_PID.setIZone(kIz);
    l_PID.setFF(kFF);
    l_PID.setOutputRange(kMinOutput, kMaxOutput);

  }

  public void move(double power){
    motor_turret_l.set(power * 0.2);
    motor_turret_r.set(power * 0.2);

    if (power < 0.1 && lswitch_front.get() == true){
      power = 0;
    }

  }


  public void stop(){
    motor_turret_l.set(0);
    motor_turret_r.set(0);

  }

  public void resetEncoders(){
    encoder_l.setPosition(0);
    encoder_r.setPosition(0);
  }
  
  public void setPOSUp(){
    l_PID.setReference(70,ControlType.kPosition);
    r_PID.setReference(72,ControlType.kPosition);

  }
  public void setPOSDown(){
    l_PID.setReference(2,ControlType.kPosition);
    r_PID.setReference(4,ControlType.kPosition);

  }

  // Putnumbers Periodicos
  @Override
  public void periodic() {
    if (lswitch_front.get() == false){
      resetEncoders();
    }

    SmartDashboard.putNumber("encoder_l", Math.round(encoder_l.getPosition()));
    SmartDashboard.putNumber("encoder_r", Math.round(encoder_r.getPosition()));
    SmartDashboard.putBoolean("lswitch front", !lswitch_front.get());

    SmartDashboard.putNumber("motor-Left", motor_turret_l.getOutputCurrent());
    SmartDashboard.putNumber("motor-Right", motor_turret_r.getOutputCurrent());


    SmartDashboard.putNumber("P Gain", kP);
    SmartDashboard.putNumber("I Gain", kI);
    SmartDashboard.putNumber("D Gain", kD);
    SmartDashboard.putNumber("I Zone", kIz);
    SmartDashboard.putNumber("Feed Forward", kFF);
    SmartDashboard.putNumber("Max Output", kMaxOutput);
    SmartDashboard.putNumber("Min Output", kMinOutput);
    SmartDashboard.putNumber("Set Rotations", 0);

    SmartDashboard.getNumber("P Gain", 0);
    SmartDashboard.getNumber("I Gain", 0);
    SmartDashboard.getNumber("D Gain", 0);
    SmartDashboard.getNumber("I Zone", 0);
    SmartDashboard.getNumber("Feed Forward", 0);
    SmartDashboard.getNumber("Max Output", 0);
    SmartDashboard.getNumber("Min Output", 0);
    SmartDashboard.getNumber("Set Rotations", 0);
  }
}
