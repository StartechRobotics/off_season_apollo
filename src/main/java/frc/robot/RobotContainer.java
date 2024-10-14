// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.Commands.DefaultCommand_chassis_mainDrive;
import frc.robot.Subsystems.Chassis;

public class RobotContainer {
  private final Chassis m_chassis = new Chassis();
  
  private final Joystick m_driverController = new Joystick(Constants.kDriverControllerPort);
  private final Joystick m_mechController = new Joystick(Constants.kMechControllerPort);

  public RobotContainer() {
    configureBindings();
    defaultCommands();
  }

  private void configureBindings() {}

  private void defaultCommands() {
    m_chassis.setDefaultCommand(new DefaultCommand_chassis_mainDrive(m_chassis, m_driverController));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
