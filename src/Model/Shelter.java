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

    // 🔥 สำคัญ: ต้องไม่ใส่ซ้ำ
    public boolean addCitizen(Citizen citizen) {

        if (citizens.contains(citizen)) {
            return false; // มีอยู่แล้ว
        }

        if (isFull()) {
            return false; // เต็ม
        }

        citizens.add(citizen);
        return true; // เพิ่มสำเร็จ
    }

    public List<Citizen> getCitizens() {
        return citizens;
    }
}
