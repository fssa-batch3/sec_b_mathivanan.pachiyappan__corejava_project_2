package in.fssa.evotingsystem.interfaces;

import in.fssa.evotingsystem.exception.PersistanceException;
import in.fssa.evotingsystem.model.Candidate;

public interface CandidateInterface extends Base<Candidate> {

	public abstract Candidate findById(int id) throws PersistanceException;

}
