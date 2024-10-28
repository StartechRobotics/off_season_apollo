// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;

public class DefaultCommand_moveTurret extends Command {

  Turret turret;
  Joystick joystick;

  double power;

  public DefaultCommand_moveTurret(Turret m_Turret, Joystick m_joystick) {
    this.turret = m_Turret;
    this.joystick = m_joystick;
    addRequirements(turret);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    power = joystick.getRawAxis(3) - joystick.getRawAxis(2);

    turret.move(power);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
