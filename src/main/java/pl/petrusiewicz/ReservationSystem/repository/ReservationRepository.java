package pl.petrusiewicz.ReservationSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.petrusiewicz.ReservationSystem.entity.ReservationEntity;

import java.util.Optional;


@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Integer> {

    Optional<ReservationEntity> getById(int id);

}
