// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Commands.Shooter_Commands.command_shoot;
import frc.robot.Commands.Shooter_Commands.command_stop;
import frc.robot.Subsystems.Chassis;
import frc.robot.Subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Autonomous extends SequentialCommandGroup {
  /** Creates a new Autonomous. */
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
      new moveBackward(chassis),
      new WaitCommand(1),
      new stopchassis(chassis)
    
      );

  }
}
