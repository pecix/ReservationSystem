package pl.petrusiewicz.ReservationSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.petrusiewicz.ReservationSystem.entity.OrganizationEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrganizationRepository extends JpaRepository<OrganizationEntity, Integer> {

    Optional<OrganizationEntity> getByName(String name);

    Optional<OrganizationEntity> getById(int id);

}
