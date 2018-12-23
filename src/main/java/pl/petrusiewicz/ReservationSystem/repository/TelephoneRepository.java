package pl.petrusiewicz.ReservationSystem.repository;

import pl.petrusiewicz.ReservationSystem.model.Telephone;

import java.util.ArrayList;
import java.util.List;

public class TelephoneRepository {

    private List<Telephone> telephones = new ArrayList<>();

    public void add(Telephone telephone){
        telephones.add(telephone);
    }

    public List<Telephone> getAll(){
        return telephones;
    }

    public Telephone get(int id){
        return telephones.get(id);
    }

    public void remove(int id){
        telephones.remove(id);
    }

    public void update(int id, Telephone telephone){
        telephones.set(id, telephone);
    }
}
