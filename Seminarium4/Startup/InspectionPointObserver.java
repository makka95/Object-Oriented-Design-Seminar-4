//-------------------------NEW CODE---------------------------------------//
package Startup;

import inspectionPointManagement.inspectionPoint;
import Model.Observer;

public class InspectionPointObserver implements Observer {

	@Override
	public void notifyNewResult(inspectionPoint point) {
		System.out.println("The result for inspection: " + point.getDescr() + " is: " + point.getResult());
	}
}
//------------------------------------------------------------------------//
