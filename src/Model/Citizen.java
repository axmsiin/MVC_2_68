package src.Model;

import java.time.LocalDate;

public class Citizen {

    private String citizenId;
    private int age;
    private String healthStatus;
    private LocalDate registerDate;
    private String citizenType;

    public Citizen(String citizenId, int age, String healthStatus,
            LocalDate registerDate, String citizenType) {
        this.citizenId = citizenId;
        this.age = age;
        this.healthStatus = healthStatus;
        this.registerDate = registerDate;
        this.citizenType = citizenType;
    }

    public String getCitizenId() {
        return citizenId;
    }

    public int getAge() {
        return age;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public LocalDate getRegisterDate() {
        return registerDate;
    }

    public String getCitizenType() {
        return citizenType;
    }
}
