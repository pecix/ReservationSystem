package pl.petrusiewicz.ReservationSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.petrusiewicz.ReservationSystem.entity.TelephoneEntity;

@Repository
public interface TelephoneRepository extends JpaRepository<TelephoneEntity, Integer> {

}
