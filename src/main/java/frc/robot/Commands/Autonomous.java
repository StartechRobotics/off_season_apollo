// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Commands.Chassis_commands.moveBackward;
import frc.robot.Commands.Chassis_commands.stopchassis;
import frc.robot.Commands.Shooter_commands.command_shoot;
import frc.robot.Commands.Shooter_commands.stopShooter;
import frc.robot.Subsystems.Chassis;
import frc.robot.Subsystems.Shooter;


public class Autonomous extends SequentialCommandGroup {

  Chassis chassis;
  Shooter shooter;

  public Autonomous(Chassis m_Chassis, Shooter m_shooter) {
    this.chassis = m_Chassis;
    this.shooter = m_shooter;

    addCommands(
      new command_shoot(shooter),
      new WaitCommand(1),
      new stopShooter(shooter),
      new WaitCommand(0.5),
      // new AutoMove(chassis)
      new moveBackward(chassis),
      new WaitCommand(2.5),
      new stopchassis(chassis)
    
      );

  }
}
