package in.fssa.evotingsystem.model;

public class User extends UserEntity {

	public User(int id, long phoneNumber, String password, String address, int voterId, int talukId, boolean isActive) {

		super.setId(id);
		super.setActive(isActive);
		super.setPhoneNumber(phoneNumber);
		super.setAddress(address);
		super.setVoterId(voterId);
		super.setTalukId(talukId);
		super.setPassword(password);

	}

	public User() {

	}
}
