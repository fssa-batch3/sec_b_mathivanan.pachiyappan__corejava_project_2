package in.fssa.evotingsystem.model;

import java.time.LocalDate;

public class CandidateEntity implements Comparable<CandidateEntity> {

	protected int Id;
	protected String candidateName;
	protected int electionId;
	protected int userId;
	protected LocalDate createdAt;
	protected boolean isActive;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public int getElectionId() {
		return electionId;
	}

	public void setElectionId(int electionId) {
		this.electionId = electionId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "CandidateEntity [Id=" + this.getId() + ", candidateName=" + this.getCandidateName() + ", electionId="
				+ this.getElectionId() + ", userId=" + this.getUserId() + ", createdAt=" + this.getCreatedAt()
				+ ", isActive=" + this.isActive() + "]";
	}

	public int compareTo(CandidateEntity o) {
		if (this.getId() == o.getId()) {
			return 0;
		} else {
			if (this.getId() < (o.getId())) {
				return -1;
			} else {
				return 1;
			}
		}
	}

}
