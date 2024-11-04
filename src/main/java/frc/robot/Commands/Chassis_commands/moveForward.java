// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands.Chassis_commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.Chassis;

public class moveForward extends Command {
  Chassis chassis;

  double error;
  double init;
  double setpoint;
  double position;

  boolean is_ready;


  public moveForward(Chassis m_chassis) {
    this.chassis = m_chassis;
    addRequirements(chassis);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {  
    // setpoint = 66;
    chassis.moveForward();

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // error = setpoint - chassis.getEncoder();
    // position = chassis.getEncoder();

    // chassis.moveForwards();

    // is_ready = (position == 66);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
