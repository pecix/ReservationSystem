package pl.petrusiewicz.ReservationSystem.service;

import org.springframework.stereotype.Service;
import pl.petrusiewicz.ReservationSystem.model.Organization;
import pl.petrusiewicz.ReservationSystem.repository.OrganizationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizationService {

    OrganizationRepository repository = new OrganizationRepository();

    public List<Organization> getAll(){
        return repository.getAll();
    }

    public Organization get(int id){
        return repository.get(id);
    }

    public void add(Organization organization){
        repository.add(organization);
    }

    public void remove(int id){
        repository.remove(id);
    }

    public void update(int id, Organization organization){
        repository.update(id, organization);
    }

    public boolean isExist(String name){
        return repository.isExist(name);
    }

    public Organization findByName(String name){
        return repository.findByName(name);
    }


}
