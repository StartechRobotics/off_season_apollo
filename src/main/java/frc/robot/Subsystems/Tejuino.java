// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.TejuinoBoard;

public class Tejuino extends SubsystemBase {
  TejuinoBoard Tejuino;

  DigitalInput lswitch;

  boolean NoteIn;

  public Tejuino() {
    Tejuino = new TejuinoBoard();
    Tejuino.init(0);

    lswitch = new DigitalInput(Constants.LSWITCH_SHOOTER);
  }

  public void TurnLEDs(){
    NoteIn = !lswitch.get();

    if (NoteIn){
      Tejuino.all_leds_red(0);
    } else {
      Tejuino.all_leds_green(0);
    }
  }

  public boolean get_note_switch(){
    NoteIn = !lswitch.get();
    return NoteIn;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
