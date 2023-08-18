package in.fssa.evotingsystem.model;

import java.time.LocalDate;

public class ElectionEntity implements Comparable<ElectionEntity> {

	protected int Id;
	protected String electionName;
	protected String boothAddress;
	protected LocalDate electionDate;
	protected int talukId;
	protected boolean isActive;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getElectionName() {
		return electionName;
	}

	public void setElectionName(String electionName) {
		this.electionName = electionName;
	}

	public String getBoothAddress() {
		return boothAddress;
	}

	public void setBoothAddress(String boothAddress) {
		this.boothAddress = boothAddress;
	}

	public LocalDate getElectionDate() {
		return electionDate;
	}

	public void setElectionDate(LocalDate electionDate) {
		this.electionDate = electionDate;
	}

	public int getTalukId() {
		return talukId;
	}

	public void setTalukId(int talukId) {
		this.talukId = talukId;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "ElectionEntity [Id=" + this.getId() + ", electionName=" + this.getElectionName() + ", boothAddress="
				+ this.getBoothAddress() + ", electionDate=" + this.getElectionDate() + ", talukId=" + this.getTalukId()
				+ ", isActive=" + this.isActive() + "]";
	}

	public int compareTo(ElectionEntity o) {
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
