package src.Controller;

import src.Model.*;
import java.time.LocalDate;
import java.util.*;

public class AssignmentController {

    private DatabaseCitizen citizenDB;
    private DatabaseShelter shelterDB;
    private DatabaseAssignment assignDB;

    public AssignmentController(
            DatabaseCitizen citizenDB,
            DatabaseShelter shelterDB,
            DatabaseAssignment assignDB) {

        this.citizenDB = citizenDB;
        this.shelterDB = shelterDB;
        this.assignDB = assignDB;

        // ✅ โหลด assignment เดิม → ใส่คนเข้า shelter ก่อน
        syncExistingAssignments();
    }

    // 🔥 เอาข้อมูล assignment เดิมมาเติม current ของ shelter
    private void syncExistingAssignments() {

        for (Assignment a : assignDB.getAll()) {

            Citizen c = citizenDB.getAll().stream()
                    .filter(x -> x.getCitizenId().equals(a.getCitizenId()))
                    .findFirst()
                    .orElse(null);

            Shelter s = shelterDB.getAll().stream()
                    .filter(x -> x.getShelterId().equals(a.getShelterId()))
                    .findFirst()
                    .orElse(null);

            if (c != null && s != null && !s.isFull()) {
                s.addCitizen(c);
            }
        }
    }

    // 🔥 จัดสรรจากข้อมูลเดิมที่มีอยู่
    public void assign() {

        List<Citizen> citizens = citizenDB.getAll();
        List<Shelter> shelters = shelterDB.getAll();

        // เด็ก + ผู้สูงอายุได้ก่อน
        citizens.sort((a, b) -> {
            boolean aP = a.getAge() < 12 || a.getAge() >= 60;
            boolean bP = b.getAge() < 12 || b.getAge() >= 60;
            return Boolean.compare(!aP, !bP);
        });

        for (Citizen c : citizens) {

            // ❌ ถ้ามี assignment แล้ว ข้าม
            if (assignDB.isAssigned(c.getCitizenId()))
                continue;

            for (Shelter s : shelters) {

                if (s.isFull())
                    continue;

                // 🔥 ตรวจ risk ให้ตรง
                if (!riskMatch(c, s))
                    continue;

                // เพิ่มคนเข้า shelter (runtime)
                s.addCitizen(c);

                // 🔥 บันทึกผลลง assignment.csv
                assignDB.add(new Assignment(
                        c.getCitizenId(),
                        s.getShelterId(),
                        LocalDate.now()));
                break;
            }
        }
    }

    // mapping ความเสี่ยง
    private boolean riskMatch(Citizen c, Shelter s) {

        switch (c.getCitizenType()) {
            case "VIP":
                return s.getRiskLevel().equals("HIGH");
            case "RISK":
                return s.getRiskLevel().equals("LOW");
            case "GENERAL":
                return s.getRiskLevel().equals("MEDIUM");
            default:
                return false;
        }
    }

    public List<Shelter> getShelters() {
        return shelterDB.getAll();
    }

    // 🔥 รายงานผลการจัดสรร
    public List<String[]> getReport() {

        List<String[]> result = new ArrayList<>();

        for (Citizen c : citizenDB.getAll()) {

            String shelterId = "UNASSIGNED";

            for (Assignment a : assignDB.getAll()) {
                if (a.getCitizenId().equals(c.getCitizenId())) {
                    shelterId = a.getShelterId();
                    break;
                }
            }

            result.add(new String[] {
                    c.getCitizenId(),
                    c.getCitizenType(),
                    shelterId,
                    shelterId.equals("UNASSIGNED")
                            ? "NOT ASSIGNED"
                            : "ASSIGNED"
            });
        }

        return result;
    }

}
