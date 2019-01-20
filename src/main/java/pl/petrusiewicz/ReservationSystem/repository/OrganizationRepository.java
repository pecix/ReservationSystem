package pl.petrusiewicz.ReservationSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.petrusiewicz.ReservationSystem.entity.OrganizationEntity;

import java.util.List;

@Repository
public interface OrganizationRepository extends JpaRepository<OrganizationEntity, Integer> {

    OrganizationEntity findByName(String name);

    OrganizationEntity findById(int id);

    boolean existsByName(String name);

    List<OrganizationEntity> findAll();

}
