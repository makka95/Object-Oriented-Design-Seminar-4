package GarageManagement;

public class Display {

	private String text;
	//-------------------------NEW CODE---------------------------------------
	private static Display onlyInstance = new Display();
	
	/**
	 * Creates an new instance of the Display class, notice the private constructor which
	 * Guarantees that only one instance of this class is created
	 */
	private Display() {
	}

	/**
	 * Returns the only instance of the Display class
	 * @return The single instance of Display class
	 */
	public static Display getDisplay() {
		return onlyInstance;
	}
	//------------------------------------------------------------------------
	/**
	 * Updates the display to show the specified string
	 * @param regNumber the string to show on the display
	 */
	public void updateDisplay(String regNumber) {
		this.text = regNumber;
	}

}
