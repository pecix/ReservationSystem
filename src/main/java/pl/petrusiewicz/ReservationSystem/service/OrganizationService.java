package pl.petrusiewicz.ReservationSystem.service;

import org.springframework.stereotype.Service;

import pl.petrusiewicz.ReservationSystem.entity.OrganizationEntity;
import pl.petrusiewicz.ReservationSystem.model.Organization;
import pl.petrusiewicz.ReservationSystem.repository.OrganizationRepository;

import java.util.List;

@Service
public class OrganizationService {

    private final OrganizationRepository repository;

    public OrganizationService(OrganizationRepository repository){
        this.repository = repository;
    }

    public List<OrganizationEntity> findAll() {
        return repository.findAll();
    }

    public OrganizationEntity findById(int id) {
        return repository.findById(id);
    }

    public boolean existById(int id) {
        return repository.existsById(id);
    }

    public boolean existByName(String name) {
        return repository.existsByName(name);
    }

    public OrganizationEntity findByName(String name) {
        return repository.findByName(name);
    }

    public OrganizationEntity add(Organization organization) {
        var organizationEntity = organization.convertToEntity();
        return repository.save(organizationEntity);
    }

    public void remove(int id) {
        repository.deleteById(id);
    }

    public void removeAll() {
        repository.deleteAll();
    }

    public void update(int id, Organization newOrganization) {
        var organizationEntity = findById(id);
        if (organizationEntity != null) {
            organizationEntity.setName(newOrganization.getName());
            repository.save(organizationEntity);
        }
    }

}
