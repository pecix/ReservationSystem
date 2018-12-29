package pl.petrusiewicz.ReservationSystem.repository;

import org.springframework.data.repository.CrudRepository;
import pl.petrusiewicz.ReservationSystem.model.Reservation;

public interface ReservationRepository extends CrudRepository<Reservation, Integer> {
}
