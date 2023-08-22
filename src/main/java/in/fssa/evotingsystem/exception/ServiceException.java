package in.fssa.evotingsystem.exception;

public class ServiceException extends Exception{

	public ServiceException(String message) {
        super(message);
    }
	
	public ServiceException(Exception exp) {
        super(exp);
    }
}
