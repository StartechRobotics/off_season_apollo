// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.Subsystems.*;

public class RobotContainer {
  private final Chassis m_chassis = new Chassis();
  private final Shooter m_shooter = new Shooter();
  private final Turret m_turret = new Turret();
  private final Climber m_climber = new Climber();
  
  private final Joystick m_driverController = new Joystick(Constants.kDriverControllerPort);
  private  final Joystick c_mechController = new Joystick(Constants.kMechControllerPort);

  public RobotContainer() {
    configureBindings();
    defaultCommands();
  }

  private void configureBindings() {
    new JoystickButton(c_mechController, Constants.SUCK_BUTTON).onTrue(new command_suck(m_shooter));

    new JoystickButton(c_mechController, Constants.SHOOT_BUTTON)
        .toggleOnTrue(new command_shoot(m_shooter))
        .toggleOnFalse(new command_stop(m_shooter));

    new POVButton(c_mechController, 0).onTrue(new command_setposUp(m_turret));
    new POVButton(c_mechController, 180).onTrue(new command_setposDown(m_turret));

    new JoystickButton(c_mechController, 6)
        .toggleOnTrue(new command_releaseClimber(m_climber))
        .toggleOnFalse(new command_stopClimber(m_climber));

    new JoystickButton(c_mechController, 5)
        .toggleOnTrue(new command_climb(m_climber))
        .toggleOnFalse(new command_stopClimber(m_climber));

    new JoystickButton(c_mechController, 3).onTrue(new testlaunch(m_shooter));
  }

  private void defaultCommands() {
    m_chassis.setDefaultCommand(new DefaultCommand_chassis_mainDrive(m_chassis, m_driverController));
    // m_turret.setDefaultCommand(new DefaultCommand_moveTurret(m_turret, c_mechController));
  }

  public Command getAutonomousCommand() {
    return new Autonomous(m_chassis,m_shooter);
  }
}