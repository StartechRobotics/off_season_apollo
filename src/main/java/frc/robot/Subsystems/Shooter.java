// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {

  TalonSRX motor_small;
  TalonSRX motor_big;
  CANSparkMax motor_back;

  DigitalInput lswitch;
  
  double power_small;
  double power_big;
  double power_back;

  boolean note_in;

  // Inicializador del shooter_---------
  public Shooter() {
    motor_small = new TalonSRX(Constants.TMOTOR_INTAKE_SMALL);
    motor_big = new TalonSRX(Constants.TMOTOR_INTAKE_BIG);
    motor_back = new CANSparkMax(Constants.SMOTOR_INTAKE_BACK, MotorType.kBrushed);

    motor_big.setNeutralMode(NeutralMode.Brake);

    lswitch = new DigitalInput(Constants.LSWITCH_SHOOTER);
  }

  // ---- Para comando SHOOT se usara charge y shoot en un intervalo de tiempo controlado
  public void charge(){
    power_small = 0.85;
    power_big = power_small*Constants.kShooterRatio;
    motor_small.set(ControlMode.PercentOutput,power_small);
    motor_big.set(ControlMode.PercentOutput,power_big);
  }

  public void launch(){
    power_back = 1;
    motor_back.set(power_back);
  }

  // mas metodos-----------

  public void stop(){
    power_small = 0;
    power_big = 0;
    power_back = 0;
    motor_small.set(ControlMode.PercentOutput, 0);
    motor_big.set(ControlMode.PercentOutput,0);
    motor_back.set(0);
  }

  // metodos del intake-----

  public void suck(){
    // note_in = !lswitch.get();
    // if (note_in == false){
      power_small = -0.8;
      power_big = -0.2;
      power_back = -0.2;

      motor_small.set(ControlMode.PercentOutput, power_small);
      motor_big.set(ControlMode.PercentOutput,power_big);
      motor_back.set(power_back);
    // }
  }

  // data retrieving methods--
  
  public boolean get_note_switch(){
    note_in = !lswitch.get();
    return note_in;
  }

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("Note in", !lswitch.get());
  }
}
