package pl.petrusiewicz.ReservationSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.petrusiewicz.ReservationSystem.model.Organization;
import pl.petrusiewicz.ReservationSystem.repository.OrganizationRepository;
import pl.petrusiewicz.ReservationSystem.utils.Utils;

import java.util.List;

@Service
public class OrganizationService {

    @Autowired
    OrganizationRepository repository;

    public List<Organization> getAll(){
        return Utils.iterableToList(repository.findAll());
    }

    public Organization get(int id){
        if(repository.findById(id).isPresent()){
            return repository.findById(id).get();
        } else {
            return null;
        }
    }

    public boolean isExist(String name){
        List<Organization> organizations = getAll();
        for(Organization organization: organizations){
            if(organization.getName().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }

    public Organization findByName(String name){
        List<Organization> organizations = getAll();
        for (Organization organization: organizations){
            if(organization.getName().equalsIgnoreCase(name)){
                return organization;
            }
        }
        return null;
    }

    public void add(Organization organization){
        repository.save(organization);
    }

    public void remove(int id){
        repository.deleteById(id);
    }

    public void update(String organizationName, Organization newOrganization){
        Organization org = findByName(organizationName);
        if (org != null){
            org.setName(newOrganization.getName());
            repository.save(org);
        }
    }

}
