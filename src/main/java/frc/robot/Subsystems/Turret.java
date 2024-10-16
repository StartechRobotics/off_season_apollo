// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import org.ejml.equation.IntegerSequence.Range;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants;

public class Turret extends SubsystemBase {
  CANSparkMax motor_shooter_l;
  CANSparkMax motor_shooter_r;

  // Limit switches - comentado hasta instalarlos
  DigitalInput lswitch_front;

  RelativeEncoder encoder_l;
  RelativeEncoder encoder_r;

  double position;
  double default_power;
  double power;
  double rotations;

  // Inicializador de clase ---------------
  public Turret() {
    motor_shooter_l = new CANSparkMax(Constants.SMOTOR_SHOOTER_L, MotorType.kBrushless);
    motor_shooter_r = new CANSparkMax(Constants.SMOTOR_SHOOTER_R, MotorType.kBrushless);
    
    motor_shooter_l.setInverted(true);
    motor_shooter_r.setInverted(false);

    motor_shooter_l.setIdleMode(IdleMode.kBrake);
    motor_shooter_r.setIdleMode(IdleMode.kBrake);

    encoder_l = motor_shooter_l.getEncoder();
    encoder_r = motor_shooter_r.getEncoder();

    lswitch_front = new DigitalInput(Constants.LSWITCH_FWD);
  }

  
  // Putnumbers Periodicos
  @Override
  public void periodic() {
    SmartDashboard.putNumber("encoder_l", encoder_l.getPosition());
    SmartDashboard.putNumber("encoder_r", encoder_r.getPosition());
    SmartDashboard.putBoolean("lswitch front", lswitch_front.get());
  }
}
