// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.event.EventLoop;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Commands.DefaultCommand_chassis_mainDrive;
import frc.robot.Commands.command_shoot;
import frc.robot.Commands.command_suck;
import frc.robot.Subsystems.*;

public class RobotContainer {
  private final Chassis m_chassis = new Chassis();
  private final Shooter m_shooter = new Shooter();
  
  private final Joystick m_driverController = new Joystick(Constants.kDriverControllerPort);
  private  final CommandJoystick c_mechController = new CommandJoystick(Constants.kMechControllerPort);

  public RobotContainer() {
    configureBindings();
    defaultCommands();
  }

  private void configureBindings() {
    c_mechController.button(Constants.SUCK_BUTTON, null).onTrue(new command_suck(m_shooter));
    c_mechController.button(Constants.SHOOT_BUTTON, null).onTrue(new command_shoot(m_shooter));
  }

  private void defaultCommands() {
    m_chassis.setDefaultCommand(new DefaultCommand_chassis_mainDrive(m_chassis, m_driverController));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
