package src.Model;

import java.time.LocalDate;

public class Assignment {

    private String citizenId;
    private String shelterId;
    private LocalDate assignDate;

    public Assignment(String citizenId, String shelterId, LocalDate assignDate) {
        this.citizenId = citizenId;
        this.shelterId = shelterId;
        this.assignDate = assignDate;
    }

    public Assignment(String citizenId, String shelterId, String assignDate) {
        this.citizenId = citizenId;
        this.shelterId = shelterId;
        this.assignDate = LocalDate.parse(assignDate);
    }

    public String getCitizenId() {
        return citizenId;
    }

    public String getShelterId() {
        return shelterId;
    }

    public LocalDate getAssignDate() {
        return assignDate;
    }
}