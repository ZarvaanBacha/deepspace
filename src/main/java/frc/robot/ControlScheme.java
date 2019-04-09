/* The ControlScheme file manages the control interface between various subsytems of the robot (ie Pneumatics)
*/
package frc.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class ControlScheme {
	
	XboxController Driver = new XboxController(0);// Assigns primary driver to controller
	XboxController secondaryDriver = new XboxController(1);// Assigns secondary driver to controller
	VictorSP roller = new VictorSP(4); // Assigns Elevator Motor
	VictorSP m_frontLeft = new VictorSP(0); // Assigns front left motor of drive train
	// follows the same assignment as m_frontLeft
	VictorSP m_rearLeft = new VictorSP(1);
	SpeedControllerGroup m_left = new SpeedControllerGroup(m_frontLeft,m_rearLeft); // assign entire left drive train to one speed control 
	// follows same assignment of left side drive train
	VictorSP m_frontRight = new VictorSP (2);
	VictorSP m_rearRight = new VictorSP (3);
	SpeedControllerGroup m_right = new SpeedControllerGroup(m_frontRight, m_rearRight);
	DifferentialDrive m_drive = new DifferentialDrive(m_left, m_right); // sets each drive train side to a differential control
	
	/*Following are variable values to determine the speed directly from the displacement in the trigger*/
	double forwardThrottle;
	double backwardThrottle;
	double throttle;
	double leftPower;
	double rightPower;
	double speed = 1;// Defualt robot speed is set to it's maximum value
	Pneumatics PneumaticControl = new Pneumatics();// Creates the penumatics object
	
	public void Drive() {
		forwardThrottle = Driver.getRawAxis(3); // Forward throttle determined by the Right Trigger
		backwardThrottle = (Driver.getRawAxis(2));// Backward throttle determined by the Left Trigger
		throttle = forwardThrottle - backwardThrottle;// Final throttle depending on the displacement in each trigger
		
		if (Driver.getRawAxis(0) > 0) {
			leftPower = throttle + Driver.getRawAxis(0);
			rightPower = throttle - Driver.getRawAxis(0);
		}else if (Driver.getRawAxis(0) < 0) {
			leftPower = throttle + Driver.getRawAxis(0);
			rightPower = throttle - Driver.getRawAxis(0);
		}else {
				leftPower = throttle;
				rightPower = throttle;
			}
		if (Driver.getRawButtonPressed(6) == true) {
			speed = 1;
		}else if (Driver.getRawButtonPressed(5) == true) {
			speed = 0.5;
		}
		//m_drive.tankDrive(((leftPower * speed)*0.9), (rightPower * speed)); // Speed changed by a percentage on left side MECHANICAL ISSUES NOW REQUIRE PROGRAMMING HAHAHA JEFF
		m_drive.tankDrive((leftPower * speed), (rightPower * speed));
		
		
		
	}
	public void Roller() {
		if ((Driver.getRawButtonPressed(2) == true) && (Driver.getRawButton(1) == false)){
			roller.set(1);
		}else if ((Driver.getRawButtonPressed(1) == true) && (Driver.getRawButton(2) == false)){
			roller.set(-1);
		}else if ((Driver.getRawButton(2) == false) && (Driver.getRawButton(1) == false)){
			roller.set(0);
		}
	}
	public void Pneumatics() {
		if (secondaryDriver.getRawButtonPressed(5) == true){
			PneumaticControl.toggleCompressor();
		}
		if (secondaryDriver.getRawButtonPressed(1) == true) {//lift
			PneumaticControl.triggerDrop();
		}
		if (secondaryDriver.getRawButtonPressed(4) == true) {//drop
			PneumaticControl.triggerLift();
		}
		if (Driver.getRawButtonPressed(1) == true) {//drop
			PneumaticControl.intakeFlapLift();
		}
		if (Driver.getRawButtonPressed(2) == true) {//drop
			PneumaticControl.intakeFlapDrop();
		}
	}
}
