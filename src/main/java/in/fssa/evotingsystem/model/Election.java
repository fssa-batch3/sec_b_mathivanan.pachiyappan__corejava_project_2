package in.fssa.evotingsystem.model;

import java.time.LocalDate;

public class Election extends ElectionEntity {

	public Election(int id, String electionName, LocalDate electionDate, String boothAddress, int talukId,
			boolean isActive) {

		super.setId(id);
		super.setActive(isActive);
		super.setBoothAddress(boothAddress);
		super.setTalukId(talukId);
		super.setElectionName(electionName);
		super.setElectionDate(electionDate);

	}

	public Election() {

	}

}
