package src.Controller;

import src.Model.*;
import java.util.List;

public class ShelterController {

    private DatabaseShelter shelterDB;

    public ShelterController(DatabaseShelter shelterDB) {
        this.shelterDB = shelterDB;
    }

    public List<Shelter> getShelters() {
        return shelterDB.getAll();
    }
}
