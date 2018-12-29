package pl.petrusiewicz.ReservationSystem.repository;

import org.springframework.data.repository.CrudRepository;
import pl.petrusiewicz.ReservationSystem.model.Organization;

public interface OrganizationRepository extends CrudRepository<Organization, Integer> {
}
