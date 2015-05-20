package GarageManagement;

public class GarageDoors {

	private boolean open;
	//-------------------------NEW CODE---------------------------------------
	private static GarageDoors singleInstance = new GarageDoors();
	
	/**
	  * Creates an new instance of the GarageDoors class, notice the private constructor which
	 * 	Guarantees that only one instance of this class is created
	 */
	private GarageDoors () {
		this.open = false;
	}
	
	/**
	 * Returns the only instance of the GarageDoors class
	 * @return The single instance of GarageDoors class
	 */
	public static GarageDoors getGarageDoors() {
		return singleInstance;
	}
	//-----------------------------------------------------------------------

	/**
	 *  Opens the garage door by setting the variable <code>open</code> to TRUE
	 */
	public void openGarageDoors() {
		this.open = true;
	}

	/**
	 *  Closes the garage door by setting the variable <code>open</code> to FALSE
	 */
	public void closeGarageDoors() {
		this.open = true;
	}

}
