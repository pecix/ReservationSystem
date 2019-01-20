package pl.petrusiewicz.ReservationSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.petrusiewicz.ReservationSystem.entity.ReservationEntity;


@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Integer> {

    ReservationEntity findById(int id);

}
