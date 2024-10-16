// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.Shooter;

public class command_suck extends Command {

  Shooter shooter;
  boolean status;


  public command_suck(Shooter m_shooter) {
    this.shooter = m_shooter;
    addRequirements(shooter);
  }

  @Override
  public void initialize() {
    shooter.suck();
  }

  @Override
  public void execute() {
    status = shooter.get_note_switch();
  }

  @Override
  public void end(boolean interrupted) {
    shooter.stop();
  }

  @Override
  public boolean isFinished() {
    return status;  
  }
}
