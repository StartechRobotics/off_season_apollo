// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands.Tejuino_commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.Tejuino;

public class NoteLED extends Command {
  Tejuino tejuino;


  
  public NoteLED(Tejuino m_Tejuino) {
    this.tejuino = m_Tejuino;
    addRequirements(tejuino);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    tejuino.TurnLEDs();
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
