package in.fssa.evotingsystem.interfaces;

import in.fssa.evotingsystem.exception.PersistanceException;
import in.fssa.evotingsystem.model.Taluk;

public interface TalukInterface extends Base<Taluk> {
	
	public abstract Taluk findById(int id) throws PersistanceException ;

}
