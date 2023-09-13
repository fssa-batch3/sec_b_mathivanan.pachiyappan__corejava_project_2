package in.fssa.evotingsystem.model;

import java.time.LocalDate;

public class Candidate extends CandidateEntity {

    public Candidate(int id, String name, int electionId, int userId, String partyName, String imageUrl, LocalDate createdAt, boolean isActive) {
        super.setId(id);
        super.setName(name);
        super.setElectionId(electionId);
        super.setUserId(userId);
        super.setPartyName(partyName);
        super.setImageUrl(imageUrl);
        super.setCreatedAt(createdAt);
        super.setActive(isActive);
    }

    public Candidate() {

    }
}
