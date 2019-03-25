package frc.robot;
import edu.wpi.first.wpilibj.*;


public class Pneumatics{
	Compressor c = new Compressor(0);
	Boolean compressorStatus = false;
	/*DoubleSolenoid S1  = new DoubleSolenoid(3,7);
	//DoubleSolenoid S2 = new DoubleSolenoid(2,6);*/ // Uncomment for climb.
	DoubleSolenoid hatchSlide  = new DoubleSolenoid(1,5);
	DoubleSolenoid hatchHook = new DoubleSolenoid(0,4);
	
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
	public void hatchSlideForward() {
		hatchSlide.set(DoubleSolenoid.Value.kForward);
	}
	public void hatchSlideBackwards() {
		hatchSlide.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void hatchHookExpansion() {
		hatchHook.set(DoubleSolenoid.Value.kForward);
	}
	
	public void hatchHookCompression() {
		hatchHook.set(DoubleSolenoid.Value.kReverse);
	}
	
	/*public void triggerLift() {
		S1.set(DoubleSolenoid.Value.kForward);
		S2.set(DoubleSolenoid.Value.kForward);
		
	}
	public void triggerDrop() {
		S1.set(DoubleSolenoid.Value.kReverse);
		S2.set(DoubleSolenoid.Value.kReverse);
		
	}*/ // Uncomment for Climb
	
}
