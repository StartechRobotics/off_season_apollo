// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Commands.Chassis_commands.moveBackward;
import frc.robot.Commands.Chassis_commands.moveForward;
import frc.robot.Commands.Chassis_commands.stopchassis;
import frc.robot.Commands.Chassis_commands.turnCommand;
import frc.robot.Commands.Shooter_commands.command_shoot;
import frc.robot.Commands.Shooter_commands.command_suck;
import frc.robot.Commands.Shooter_commands.stopShooter;
import frc.robot.Commands.Turret_commands.command_setposDown;
import frc.robot.Commands.Turret_commands.command_setposUp;
import frc.robot.Subsystems.*;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class midTwoNotes extends SequentialCommandGroup {
  Chassis chassis;
  Shooter shooter;
  Turret turret;

  public midTwoNotes(Chassis m_Chassis, Shooter m_Shooter, Turret m_Turret) {
    this.chassis = m_Chassis;
    this.turret = m_Turret;
    this.shooter = m_Shooter;
    addCommands(
      new command_shoot(shooter),
      new WaitCommand(1),
      new stopShooter(shooter),
      new WaitCommand(0.5),
      new moveBackward(m_Chassis),
      new WaitCommand(0.2),
      new turnCommand(chassis, 90),
      new stopchassis(m_Chassis),
      new command_setposDown(m_Turret),
      new moveForward(m_Chassis).alongWith(new command_suck(m_Shooter)),
      new stopchassis(m_Chassis),
      new moveBackward(m_Chassis),
      new WaitCommand(0.2),
      new stopchassis(m_Chassis),
      new turnCommand(m_Chassis, 180),
      new moveForward(m_Chassis),
      new WaitCommand(0.5),
      new command_setposUp(m_Turret),
      new command_shoot(m_Shooter),
      new stopShooter(m_Shooter)
    );
  }
}
