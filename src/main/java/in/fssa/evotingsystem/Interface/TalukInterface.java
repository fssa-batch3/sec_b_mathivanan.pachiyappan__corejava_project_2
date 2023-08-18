package in.fssa.evotingsystem.Interface;

import in.fssa.evotingsystem.model.Taluk;

public interface TalukInterface extends Base<Taluk> {
	
	public abstract Taluk findById(int id);

	public abstract int count();


}
