package in.fssa.evotingsystem.model;

import java.time.LocalDate;

public class Candidate extends CandidateEntity {

	public Candidate(int id, String candidate_name, LocalDate created_at, int election_id, int user_id,
			boolean isActive) {

		super.setId(id);
		super.setActive(isActive);
		super.setCandidateName(candidate_name);
		super.setElectionId(election_id);
		super.setUserId(user_id);
		super.setCreatedAt(created_at);

	}

	public Candidate() {

	}
}
