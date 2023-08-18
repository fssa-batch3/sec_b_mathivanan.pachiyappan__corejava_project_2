package in.fssa.evotingsystem.model;

public class UserEntity implements Comparable<UserEntity> {

	private int Id;
	private long phoneNumber;
	private String password;
	private String address;
	private int voterId;
	private int talukId;
	private boolean isActive;

	public int getId() {
		return Id;
	}

	public void setId(int Id) {
		this.Id = Id;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getVoterId() {
		return voterId;
	}

	public void setVoterId(int voterId) {
		this.voterId = voterId;
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
		return "UserEntity [Id=" + this.getId() + ", phoneNumber=" + this.getPhoneNumber() + ", password="
				+ this.password + ", address=" + this.getAddress() + ", voterId=" + this.getVoterId() + ", talukId="
				+ this.getTalukId() + ", isActive=" + this.isActive() + "]";
	}

	public int compareTo(UserEntity o) {
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
