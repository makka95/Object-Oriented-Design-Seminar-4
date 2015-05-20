package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import inspectionPointManagement.InvalidRegNumberException;
import inspectionPointManagement.inspectionPointManager;
import inspectionPointManagement.inspectionPoint;

public class Vehicle {

	private String regNumber;

	private List<inspectionPoint> inspectionPointList;
	private ListIterator<inspectionPoint> iterator;
	//-------------------------NEW CODE---------------------------------------//
	private List<Observer> obs; 
	//------------------------------------------------------------------------//

	private inspectionPointManager inspectionPointManager;

	/**
	 * Creates an new instance of the <code>Vehicle</code> class, also calls the <code>getInspectionPoints()</code> function to update the inspectionPointList
	 * @param regNumber, the registration number of the vehicle, entered in the view and passed through controller and inspection classes
	 * @throws InvalidRegNumberException InvalidRegNumberException if the specified regNumber does not have any inspection points
	 */
	public Vehicle(String regNumber) throws InvalidRegNumberException {
		this.regNumber = regNumber;
		this.inspectionPointManager = new inspectionPointManager(this.regNumber);
		this.inspectionPointList = new ArrayList<inspectionPoint>();
		getInspectionPoints();
		this.iterator = this.inspectionPointList.listIterator();
		//-------------------------NEW CODE---------------------------------------//
		obs = new ArrayList<>();
		//------------------------------------------------------------------------//
	}
	
	//-------------------------NEW CODE---------------------------------------//
	/**
	 * Adds an observer that shall be notified when changes occur in any inspectionPoint
	 * @param observer The observer that shall be added to the list
	 */
	public void addObserver(Observer observer) {
		obs.add(observer);
	}
	
	/**
	 * Notifies all observers that an inspectionPoint has been updated
	 * @param point The inspectionPoint which was updated
	 */
	public void notifyObservers(inspectionPoint point) {
		for (Observer observer : obs) {
			observer.notifyNewResult(point);
		}
	}
	//------------------------------------------------------------------------//
	
	/**
	 * Resets the list iterator for the <code>inspectionPoint</code> list
	 */
	public void resetListIterator() {
		iterator = inspectionPointList.listIterator();
	}

	/**
	 *  Calculates the total cost of all the inspection points in the <code>inspectionPointList</code>
	 */
	public int getTotalCost() {
		this.resetListIterator();
		inspectionPoint point;
		boolean iterate = true;
		int cost = 0;
		
		while(iterate) {
			
			point = this.getNextInspectionPoint();
			
			if (point == null) {
				iterate = false;
			} else {
				cost += point.getCost();
			}
		}
		return cost;
	}

	/**
	 *  Sets the inspection point result of an <code>inspectionPoint</code> instance in the <code>inspectionPointList</code>
	 *  Also calls the notifyObservers method to notify all observers of the change
	 */
	public void setInspectionPointResult(int num, Boolean result) {
		this.inspectionPointList.get(num).setResult(result);
		notifyObservers(this.inspectionPointList.get(num));
	}

	/**
	 *  Gets the inspection points for the vehicle from the <code>inspectionPointManager</code> class
	 */
	private void getInspectionPoints() {
		this.inspectionPointList = this.inspectionPointManager.getAvailableInspectionPoints();
	}
	
	/**
	 * Gets the next inspectionPoint from the inspectionPoint list
	 * @return Returns the next inspection point if it exists, Returns null if there is no moer inspection points
	 */
	public inspectionPoint getNextInspectionPoint() {
		try {
			return this.iterator.next();
		} catch(NoSuchElementException exc) {
			return null;
		}
	}
	
	/**
	 * Gets the regNumber variable of the specific instance of the <code>Vehicle</code> class
	 * @return returns the regNumber variable
	 */
	public String getRegNumber() {
		return this.regNumber;
	}
	
	/**
	 * Updates the database with the current inspection point list
	 */
	
	public void updateDatabase() {
		if(!this.inspectionPointManager.updateDatabase(this.inspectionPointList)) {
			System.out.println("There was an error updating the database");
		}
	}
}
