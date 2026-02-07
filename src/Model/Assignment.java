package src.Model;

import java.time.LocalDate;

public class Assignment {

    private String citizenId;
    private String shelterId;
    private LocalDate assignDate;

    // ใช้ตอน assign ใหม่ในระบบ
    public Assignment(String citizenId, String shelterId, LocalDate assignDate) {
        this.citizenId = citizenId;
        this.shelterId = shelterId;
        this.assignDate = assignDate;
    }

    // 🔥 ใช้ตอนอ่านจาก CSV
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
