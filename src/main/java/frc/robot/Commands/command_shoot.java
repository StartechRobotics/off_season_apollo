

package frc.robot.Commands;


import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;

public class command_shoot extends Command {
  Shooter shooter;
  public Timer timer;
  public double timeinMs;
  boolean is_ready;
  

  public command_shoot(Shooter m_shooter) {
    this.shooter = m_shooter;
    addRequirements(shooter);
    
    timer = new Timer();
    is_ready = false;
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
    timeinMs = timer.get()*1000;
    is_ready = timer.get()*1000 > Constants.kChargeTimeMs;
    SmartDashboard.putNumber("time", timeinMs);
    
  }

  @Override
  public void end(boolean interrupted) {
    shooter.launch();

  }

  @Override
  public boolean isFinished() {
    return is_ready;
  }
}
