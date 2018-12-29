package pl.petrusiewicz.ReservationSystem.repository;

import org.springframework.data.repository.CrudRepository;
import pl.petrusiewicz.ReservationSystem.model.Telephone;

public interface TelephoneRepository extends CrudRepository<Telephone, Integer> {
}
