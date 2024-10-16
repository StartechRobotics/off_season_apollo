

package frc.robot.Commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.Subsystems.Shooter;

public class command_shoot extends Command {
  Shooter shooter;
  Timer timer;
  boolean is_ready = false;

  public command_shoot(Shooter m_shooter) {
    this.shooter = m_shooter;
    addRequirements(shooter);
    timer = new Timer();
  }

  @Override
  public void initialize() {
    shooter.stop();
    shooter.charge();
    timer.reset();
    timer.start();
  }

  @Override
  public void execute() {
    is_ready = timer.get()*1000 > Constants.kChargeTimeMs;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shooter.launch();
    new WaitCommand(2);
    shooter.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return is_ready;
  }
}
