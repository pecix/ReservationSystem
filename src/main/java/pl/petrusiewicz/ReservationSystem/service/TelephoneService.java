package pl.petrusiewicz.ReservationSystem.service;

import org.springframework.stereotype.Service;
import pl.petrusiewicz.ReservationSystem.model.Telephone;
import pl.petrusiewicz.ReservationSystem.repository.TelephoneRepository;

import java.util.List;

@Service
public class TelephoneService {

    TelephoneRepository repository = new TelephoneRepository();

    public List<Telephone> getAll(){
        return repository.getAll();
    }

    public Telephone get(int id){
        return repository.get(id);
    }

    public void add(Telephone telephone){
        repository.add(telephone);
    }

    public void remove(int id){
        repository.remove(id);
    }

    public void update(int id, Telephone telephone){
        repository.update(id, telephone);
    }

}
