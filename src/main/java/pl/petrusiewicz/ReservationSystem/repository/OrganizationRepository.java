package pl.petrusiewicz.ReservationSystem.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pl.petrusiewicz.ReservationSystem.model.Organization;

import java.util.List;

@Repository
public interface OrganizationRepository extends PagingAndSortingRepository<Organization, Integer> {

    Organization findByName(String name);

    Organization findById(int id);

    boolean existsByName (String name);

    List<Organization> findAll();


}
