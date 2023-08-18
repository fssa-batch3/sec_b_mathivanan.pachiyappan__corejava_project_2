package in.fssa.evotingsystem.model;

public class TalukEntity {

	protected int Id;
	protected String talukName;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getTalukName() {
		return talukName;
	}

	public void setTalukName(String talukName) {
		this.talukName = talukName;
	}

	@Override
	public String toString() {
		return "TalukEntity [Id=" + this.getId() + ", talukName=" + this.getTalukName() + "]";
	}
	
	public int compareTo(TalukEntity o) {
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
