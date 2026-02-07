package src.Controller;

import src.Model.*;
import java.util.*;

public class CitizenController {

    private DatabaseCitizen db = new DatabaseCitizen();

    // แสดงทั้งหมด
    public List<Citizen> getAll() {
        return db.getAll();
    }

    // แยกตามประเภทประชาชน
    public List<Citizen> getByType(String type) {

        if (type.equals("ALL"))
            return db.getAll();

        List<Citizen> result = new ArrayList<>();

        for (Citizen c : db.getAll()) {
            if (c.getCitizenType().equalsIgnoreCase(type)) {
                result.add(c);
            }
        }
        return result;
    }
}
