// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.Chassis;

public class AutoMove extends Command {
  Chassis chassis;

  double lerror;
  double rerror;

  double leftset;
  double rightset;

  boolean is_ready;


  public AutoMove(Chassis m_Chassis) {
    this.chassis = m_Chassis;
    addRequirements(chassis);

    leftset = 66;
    rightset = 88;

    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // lerror = leftset - chassis.getEncoder();
    
    rerror = 0;

    // chassis.AutoMove(lerror, rerror);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    is_ready = lerror == 0;

    return is_ready;
  }
}
