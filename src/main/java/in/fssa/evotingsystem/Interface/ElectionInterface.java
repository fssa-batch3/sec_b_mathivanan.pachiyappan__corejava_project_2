package in.fssa.evotingsystem.Interface;

import in.fssa.evotingsystem.exception.PersistanceException;
import in.fssa.evotingsystem.model.Election;

public interface ElectionInterface extends Base<Election> {

	public abstract Election findById(int id) throws PersistanceException;

}
