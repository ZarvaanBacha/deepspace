/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        *
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot;
import edu.wpi.first.wpilibj.*;
// we back
public class Robot extends IterativeRobot {
	ControlScheme TeleopControl = new ControlScheme();
	
	@Override
	public void robotInit() {
		CameraServer.getInstance().startAutomaticCapture(0).setResolution(160, 120);
		CameraServer.getInstance().startAutomaticCapture(1).setResolution(160, 120);
		CameraServer.getInstance().getServer();
	}

	@Override
	public void teleopPeriodic() {
		TeleopControl.Drive();
		TeleopControl.Elevator();
		TeleopControl.Pneumatics();
	}	
	
	@Override	
	public void autonomousPeriodic() {
		TeleopControl.Drive();
		TeleopControl.Elevator();
		TeleopControl.Pneumatics();
	}

	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
