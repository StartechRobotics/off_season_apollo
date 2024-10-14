package frc.robot.Commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.Chassis;

public class DefaultCommand_chassis_mainDrive extends Command {
  Chassis R_chassis;
  Joystick joystick;

  private double turn;
  private double drive;

  public DefaultCommand_chassis_mainDrive(Chassis chassis, Joystick joystick) {
    this.R_chassis = chassis; 
    this.joystick = joystick;
    addRequirements(R_chassis);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drive = joystick.getRawAxis(2) - joystick.getRawAxis(3);
    turn = joystick.getRawAxis(0) * 0.6; 

    R_chassis.mainDrive(drive,turn);

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
