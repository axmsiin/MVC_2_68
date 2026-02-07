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

        syncExistingAssignments();
    }

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

    public void assign() {

        List<Citizen> citizens = citizenDB.getAll();
        List<Shelter> shelters = shelterDB.getAll();

        citizens.sort((a, b) -> {
            boolean aP = a.getAge() < 12 || a.getAge() >= 60;
            boolean bP = b.getAge() < 12 || b.getAge() >= 60;
            return Boolean.compare(!aP, !bP);
        });

        for (Citizen c : citizens) {

            if (assignDB.isAssigned(c.getCitizenId()))
                continue;

            for (Shelter s : shelters) {

                if (s.isFull())
                    continue;

                if (!riskMatch(c, s))
                    continue;

                s.addCitizen(c);

                assignDB.add(new Assignment(
                        c.getCitizenId(),
                        s.getShelterId(),
                        LocalDate.now()));
                break;
            }
        }
    }

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