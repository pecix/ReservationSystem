package pl.petrusiewicz.ReservationSystem.service;

import org.springframework.stereotype.Service;

import pl.petrusiewicz.ReservationSystem.entity.OrganizationEntity;
import pl.petrusiewicz.ReservationSystem.model.Organization;
import pl.petrusiewicz.ReservationSystem.repository.OrganizationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizationService {

    private final OrganizationRepository repository;

    public OrganizationService(OrganizationRepository repository){
        this.repository = repository;
    }

    public List<OrganizationEntity> getAll() {
        return repository.findAll();
    }

    public Optional<OrganizationEntity> getById(int id) {
        return repository.getById(id);
    }

    public Optional<OrganizationEntity> getByName(String name) {
        return repository.getByName(name);
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
        var organizationEntity = getById(id);
        if (organizationEntity.isPresent()) {
            organizationEntity.get().setName(newOrganization.getName());
            repository.save(organizationEntity.get());
        }
    }

}
