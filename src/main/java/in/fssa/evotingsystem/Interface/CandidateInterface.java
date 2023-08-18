package in.fssa.evotingsystem.Interface;

import in.fssa.evotingsystem.model.Candidate;

public interface CandidateInterface extends Base<Candidate> {

	public abstract Candidate findById(int id);

	public abstract int count();

}
