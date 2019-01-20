package pl.petrusiewicz.ReservationSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.petrusiewicz.ReservationSystem.model.Organization;
import pl.petrusiewicz.ReservationSystem.repository.OrganizationRepository;

import java.util.List;

@Service
public class OrganizationService {

    @Autowired
    OrganizationRepository repository;

    public List<Organization> findAll() {
        return repository.findAll();
    }

    public Organization findById(int id) {
        return repository.findById(id);
    }

    public boolean existById(int id){
        return repository.existsById(id);
    }

    public boolean existByName(String name){
        return repository.existsByName(name);
    }

    public Organization findByName(String name) {
        return repository.findByName(name);
    }

    public void add(Organization organization) {
        repository.save(organization);
    }

    public void remove(int id) {
        repository.deleteById(id);
    }

    public void removeAll(){
        repository.deleteAll();
    }

    public void update(int id, Organization newOrganization) {
        var organization = findById(id);
        if (organization != null) {
            organization.setName(newOrganization.getName());
            repository.save(organization);
        }
    }

}
