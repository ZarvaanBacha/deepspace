package frc.robot;
import edu.wpi.first.wpilibj.*;


public class Pneumatics{
	Compressor c = new Compressor(0);
	Boolean compressorStatus = false;
	/*DoubleSolenoid S1  = new DoubleSolenoid(3,7);
	//DoubleSolenoid S2 = new DoubleSolenoid(2,6);*/ // Uncomment for climb.
	DoubleSolenoid rightLift  = new DoubleSolenoid(1,5);
	DoubleSolenoid leftLift = new DoubleSolenoid(0,4);
	DoubleSolenoid intakeFlap = new DoubleSolenoid(2,6);
	
	public void chargeCompressor() {
		c.start();
		compressorStatus = true;

	}
	public void killCompressor() {
		c.stop();
		compressorStatus = false;
	}
	
	public void toggleCompressor() {
		c.setClosedLoopControl(true);
		if (compressorStatus == true) {
			killCompressor();
		}else {
			chargeCompressor();
		}
	}
	public void triggerLift(){
		leftLift.set(DoubleSolenoid.Value.kForward);
		rightLift.set(DoubleSolenoid.Value.kForward);
	}
	public void triggerDrop(){
		leftLift.set(DoubleSolenoid.Value.kReverse);
		rightLift.set(DoubleSolenoid.Value.kReverse);
	}
	public void intakeFlapLift(){
		intakeFlap.set(DoubleSolenoid.Value.kForward);
	}

	public void intakeFlapDrop(){
		intakeFlap.set(DoubleSolenoid.Value.kReverse);
	}
	
}
