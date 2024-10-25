// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climber extends SubsystemBase {
  CANSparkMax motor_right;
  CANSparkMax motor_left;

 
  public Climber() {
    motor_left = new CANSparkMax(Constants.SMOTOR_CLIMBER_L, MotorType.kBrushless);
    motor_right = new CANSparkMax(Constants.SMOTOR_CLIMBER_R, MotorType.kBrushless);

    motor_right.restoreFactoryDefaults();
    motor_left.restoreFactoryDefaults();

    motor_left.setInverted(true);
    motor_right.setInverted(false);


    motor_left.setIdleMode(IdleMode.kBrake);
    motor_right.setIdleMode(IdleMode.kBrake);

  }

  public void ReleaseClimber(){
    motor_left.set(0.4);
    motor_right.set(0.4);
  }

  public void stop(){
    motor_left.set(0);
    motor_right.set(0);
  }

  public void Climb(){
    motor_left.set(-0.4);
    motor_right.set(-0.4);
  }

  @Override
  public void periodic() {

    SmartDashboard.putNumber("climber derecho", motor_right.getAppliedOutput());
    SmartDashboard.putNumber("climber izquierdo", motor_left.getAppliedOutput());

    // This method will be called once per scheduler run
  }
}
