package inspectionPointManagement;

import java.util.ArrayList;
import java.util.List;

public class inspectionPointManager {

	private List<inspectionPoint> availableInspectionPoints;
	private String regNumber;

	/**
	 * Creates an new instance of the <code>inspectionPointManager</code> for the <code>Vehicle</code> class with the specified regNumber
	 * @param regNumber, the registration number of the inspected vehicle
	 * @throws InvalidRegNumberException InvalidRegNumberException if the specified regNumber does not have any inspection points
	 */
	public inspectionPointManager(String regNumber) throws InvalidRegNumberException {
		this.regNumber = regNumber;
		this.availableInspectionPoints = new ArrayList<inspectionPoint>();
		createInspectionPointList();
	}

	/**
	 *  Returns the inspection points in the list <code>availableInspectionPoints</code>
	 */
	public List<inspectionPoint> getAvailableInspectionPoints() {
		return availableInspectionPoints;
	}

	/**
	 * Retrieves an list of <code>InspectionPoint<code> for the vehicle with the registration number regNumber from the database
	 * In this case(where there is no database) it creates several new <code>inspectionPoints</code> for use
	 * @throws InvalidRegNumberException if the specified regNumber does not have any inspection points
	 */
	private void createInspectionPointList() throws InvalidRegNumberException {
		
		//-------------------------NEW CODE---------------------------------------
		//changed code! added an if-case to check the regNumber by calling checkRegNumber()
		if(checkRegNumber()) {
		
			String[] descriptions = {"Inspect Tires", "Inspect Engine", "Inspect Lights", "Inspect Brakes", "Inspect Airbags", "Inspect AC", "Inspect Electrical System"};
			int num = 0;
			
			for (String s: descriptions) {
				inspectionPoint point = new inspectionPoint(num, this.regNumber, s);
				this.availableInspectionPoints.add(num, point);
				num++;
			}
		} else {
			throw new InvalidRegNumberException();
		}
		//-----------------------------------------------------------------------
	}
	
	
	//-------------------------NEW CODE---------------------------------------
	/**
	 * Checks if the regNumber is valid
	 * @return returns TRUE if regNumber is valid, returns FALSE if regNumber is invalid
	 */
	private boolean checkRegNumber() {
		
		String validRegNumbers = "MN";
		
		if(this.regNumber.toLowerCase().contains(validRegNumbers.toLowerCase())) {
			return true;
		} else {
			return false;
		}
	}
	//------------------------------------------------------------------------
	
	/**
	 * 	Updates the database with the result of the inspection points
	 * @param inspectionPointResult, the list to send to the database for the desired vehicle
	 * @return, returns TRUE if database was update correctly, returns FALSE if there was an error
	 */
	public boolean updateDatabase(List<inspectionPoint> inspectionPointResult) {
		if(inspectionPointResult == null || inspectionPointResult.isEmpty()) {
			throw new NullPointerException("Cannot send an empty list to database");
		} else {
			printResult();
			return true;
		}
	}
	
	/**
	 * Prints the paper with the result from the inspection
	 */
	private void printResult() {
		System.out.println("Would have printed a inspection result papaer if we had an printer...");
	}

}
