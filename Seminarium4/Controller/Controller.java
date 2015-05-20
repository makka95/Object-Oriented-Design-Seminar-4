package Controller;

import Model.Inspection;
import Model.Observer;
import GarageManagement.Display;
import GarageManagement.GarageDoors;
import inspectionPointManagement.InvalidRegNumberException;
import inspectionPointManagement.inspectionPoint;

public class Controller {

	private Inspection inspection;
	private GarageDoors garageDoors;
	private Display display;

	/**
	 * creates a new instance of the Inspection class.
	 * @param regNumber, the registration number of the inspected vehicle, entered in the view
	 * @throws InvalidRegNumberException InvalidRegNumberException if the specified regNumber does not have any inspection points
	 */
	public void startNewInspection(String regNumber) throws InvalidRegNumberException {
		this.inspection = new Inspection(regNumber);
		//-------------------------NEW CODE---------------------------------------
		this.garageDoors = GarageDoors.getGarageDoors();
		this.display = Display.getDisplay();
		//------------------------------------------------------------------------
	}

	/**
	 *  Gets the next inspectionPoint in the list from the vehicle class, goes through inspection class
	 */
	public inspectionPoint getNextInspectionPoint() {
		return inspection.getNextInspectionPoint();
	}

	/**
	 * Calls the corresponding function in the <code>Inspection</code> class for setting the inspection point result of the vehicle
	 * @param num, the ID number of the inspection point
	 * @param result, The boolean result of the inspectionPoint's result
	 */
	public void enterInspectionResult(int num, boolean result) {
		inspection.setInspectionResult(num, result);
	}

	/**
	 *  Closes the garage doors by calling the corresponding function in the <code>GarageDoors</code> class
	 */
	public void closeGarageDoors() {
		garageDoors.closeGarageDoors();
	}

	/**
	 *  Opens the garage doors by calling the corresponding function in the <code>GarageDoors</code> class
	 */
	public void openGarageDoors() {
		garageDoors.openGarageDoors();
	}
	/**
	 * Updates the display
	 * @param regNumber the String to show on the display
	 */
	
	public void updateDisplay(String regNumber) {
		display.updateDisplay(regNumber);
	}
	
	/**
	 * Returns the cost of the inspection
	 * @return returns the cost
	 */
	
	public int getCost() {
		return inspection.getCost();
	}
	
	/**
	 * Makes a new payment through the inspection class
	 * @param cardNum cardNum The number of the card used for the payment, set to null if payment is done by cash
	 */
	public void makePayment(String cardNum) {
		inspection.makePayment(cardNum);
	}
	
	/**
	 * Updates the database with the current inspection point list
	 */
	public void updateDatabase() {
		inspection.updateDatabase();
	}
	
	/**
	 * Resets the iterator for the inspectionPoints contained in the list for the vehicle class
	 */
	public void resetInspectionPointIterator() {
		inspection.resetInspectionPointIterator();
	}
	
	
	//-------------------------NEW CODE---------------------------------------
	/**
	 * Adds an observer to the vehicle class observer list
	 * @param observer The observer to be added to the list
	 */
	public void addObserver(Observer observer) {
		inspection.addObserver(observer);
	}
	//------------------------------------------------------------------------
}
