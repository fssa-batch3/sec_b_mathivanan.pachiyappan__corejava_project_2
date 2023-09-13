package in.fssa.evotingsystem.model;

import java.time.LocalDate;

public class CandidateEntity implements Comparable<CandidateEntity> {

    protected int id; // Updated to match the SQL table column name
    protected int userId; // Updated to match the SQL table column name
    protected int electionId;
    protected String name; // Updated to match the SQL table column name
    protected String partyName;
    protected String imageUrl; // Added to match the SQL table
    protected LocalDate createdAt;
    protected boolean isActive;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getElectionId() {
        return electionId;
    }

    public void setElectionId(int electionId) {
        this.electionId = electionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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
        return "CandidateEntity [id=" + id + ", userId=" + userId + ", electionId=" + electionId + ", name=" + name
                + ", partyName=" + partyName + ", imageUrl=" + imageUrl + ", createdAt=" + createdAt + ", isActive="
                + isActive + "]";
    }

    public int compareTo(CandidateEntity o) {
        if (this.id == o.id) {
            return 0;
        } else {
            return Integer.compare(this.id, o.id);
        }
    }
}
