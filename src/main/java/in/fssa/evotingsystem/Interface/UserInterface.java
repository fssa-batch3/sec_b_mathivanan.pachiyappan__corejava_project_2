package in.fssa.evotingsystem.Interface;

import in.fssa.evotingsystem.exception.PersistanceException;
import in.fssa.evotingsystem.model.User;

public interface UserInterface extends Base<User> {
	
	public abstract User findById(int id) throws PersistanceException;

}