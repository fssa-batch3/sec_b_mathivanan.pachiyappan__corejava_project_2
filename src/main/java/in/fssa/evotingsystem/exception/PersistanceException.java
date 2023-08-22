package in.fssa.evotingsystem.exception;

public class PersistanceException extends Exception {

	public PersistanceException(String message) {
        super(message);
    }
	
	public PersistanceException(Exception exp) {
        super(exp);
    }
}
