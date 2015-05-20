package Model;

import inspectionPointManagement.inspectionPoint;

public interface Observer {

	/**
	 * Called when the specified inspectionPoint changes the result
	 * @param point the inspectionPoint which the result was changed for
	 */
	void notifyNewResult(inspectionPoint point);
}
