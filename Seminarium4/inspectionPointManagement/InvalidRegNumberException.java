//-------------------------NEW CODE---------------------------------------
package inspectionPointManagement;
/**
 * New code! an exception class to show that an invalid regNumber has been entered
 * @author Marcus
 *
 */
@SuppressWarnings("serial")
public class InvalidRegNumberException extends Exception {

	public InvalidRegNumberException() {
		super("There is no inspections for the entered registration number");
	}
}
//------------------------------------------------------------------------