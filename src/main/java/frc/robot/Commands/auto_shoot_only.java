// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Commands.Shooter_commands.command_shoot;
import frc.robot.Commands.Shooter_commands.stopShooter;
import frc.robot.Subsystems.Shooter;

public class auto_shoot_only extends SequentialCommandGroup {
  Shooter shooter;
  /** Creates a new auto_shoot_only. */
  public auto_shoot_only(Shooter m_shooter) {
   this.shooter = m_shooter;
   addCommands(
    new command_shoot(shooter),
      new WaitCommand(1),
      new stopShooter(shooter)
   );
  }

}
