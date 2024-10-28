// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;

public class command_releaseClimber extends Command {
  Climber climber;

  double power;

  public command_releaseClimber(Climber m_climber) {
    this.climber = m_climber;
    addRequirements(climber);
    
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    climber.ReleaseClimber();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // power = joystick.getRawAxis(3) - joystick.getRawAxis(2);

    // climber.climb(power);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
