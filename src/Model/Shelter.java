package src.Model;

import java.util.ArrayList;
import java.util.List;

public class Shelter {

    private String shelterId;
    private int capacity;
    private String riskLevel;

    private List<Citizen> citizens = new ArrayList<>();

    public Shelter(String shelterId, int capacity, String riskLevel) {
        this.shelterId = shelterId;
        this.capacity = capacity;
        this.riskLevel = riskLevel;
    }

    public String getShelterId() {
        return shelterId;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public int getCurrent() {
        return citizens.size();
    }

    public boolean isFull() {
        return citizens.size() >= capacity;
    }

    public boolean addCitizen(Citizen citizen) {

        if (citizens.contains(citizen)) {
            return false; 
        }

        if (isFull()) {
            return false; 
        }

        citizens.add(citizen);
        return true; 
    }

    public List<Citizen> getCitizens() {
        return citizens;
    }
}