// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands.Chassis_commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.Subsystems.Chassis;

public class turnCommand extends Command {

  Chassis chassis;
  double turn_degrees;
  PIDController pidController = new PIDController(Constants.kP_turn, Constants.kI_turn, Constants.KD_turn);

  public turnCommand(Chassis m_Chassis, double degrees) {
    this.chassis = m_Chassis;
    this.turn_degrees = degrees;
    addRequirements(m_Chassis);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    chassis.turn(chassis.getGyro(), turn_degrees, pidController);
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return pidController.atSetpoint();
  }
}
