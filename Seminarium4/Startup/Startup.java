package Startup;

import inspectionPointManagement.InvalidRegNumberException;
import inspectionPointManagement.inspectionPoint;

import java.util.Scanner;

import Controller.Controller;
import Model.Observer;

/**
 * Starts the program and runs an sample execution
 * @author Marcus
 *
 */
public class Startup{

	private Controller controller;
	private Observer obs;

	/**
	 * Creates an new instance of the <code>View</code> class of the application
	 * @param control, passes through an referees to the control class created in the <code>startUp</code> class
	 */
	
	/**
	 * The main method for the program
	 * @param args - this program does not handle any parameters
	 */
	public Startup() {
		this.controller = new Controller();
		//-------------------------NEW CODE---------------------------------------
		obs = new InspectionPointObserver();
		//------------------------------------------------------------------------
	}
	
	/**
	 * An sample execution of the program
	 */
	public void sampleExecution() {
		
		//Create input scanner
		Scanner in = new Scanner(System.in);
		boolean done = false;
		String regNumber = "";
		
		//Starts new inspection
		System.out.println("Enter a registration number for the vehicle: \n");
		while(!done) {
			try{
				regNumber = in.nextLine();
				controller.startNewInspection(regNumber);
				done = true;
			} catch(IllegalArgumentException exc) {
				System.out.println("(IllegalArgumentException thrown!)Invalid registration number for the vehicle, length of regNumber not supported, try again: \n");
			} 
			//-------------------------NEW CODE---------------------------------------
			catch(InvalidRegNumberException exc) {
				//NEW CODE! catch block to catch the new exception for regNumbers with no inspections
				System.out.println("(InvalidRegNumberException thrown!)There are no inspection points for the entered registration number, try again: \n");
			}
			//------------------------------------------------------------------------
		}
		System.out.println("Inspection Started! \n");
		
		//-------------------------NEW CODE---------------------------------------
		//Add ObserverImpl as observer to vehicle
		controller.addObserver(obs);
		//------------------------------------------------------------------------
		
		//Updates Display and open garage doors
		controller.updateDisplay(regNumber);
		controller.openGarageDoors();
		
		//Customer enters garage, close garage doors
		controller.closeGarageDoors();
		
		//Get cost for inspection
		System.out.println("The cost of the inspection is: " + controller.getCost() + " kr \n");
		
		//Customer enters card information
		System.out.println("Enter a valid cardnumber or leave it blank to pay by cash: \n");
		done = false;
		while(!done) {
			try{
				controller.makePayment(in.nextLine());
				done = true;
			} catch(IllegalArgumentException exc) {
				System.out.println("Invalid card number, try again: \n");
			}
		}
		
		//Perform inspection
		done = false;
		controller.resetInspectionPointIterator();
		while(!done) {
			
			//Gets next inspection point
			inspectionPoint point = controller.getNextInspectionPoint();
			if(point != null) {
				//-------------------------NEW CODE---------------------------------------
				//Sets the result of the inspection(causes the vehicle to call the notifyNewResult)
				controller.enterInspectionResult(point.getNum(), true);
				//------------------------------------------------------------------------
			} else {
				//If there are no more inspection points, we are done
				done = true;
			}
		}
		
		//Store inspection result and prints them
		controller.updateDatabase();
		
		//Open garage doors for customer to leave
		controller.openGarageDoors();
		
		in.close();

	}
}
