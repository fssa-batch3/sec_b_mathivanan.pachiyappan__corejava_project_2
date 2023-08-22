package in.fssa.evotingsystem.exception;

public class ValidationException extends Exception{

	public ValidationException(String message) {
        super(message);
    }
	
	public ValidationException(Exception exp) {
        super(exp);
    }
	
}
