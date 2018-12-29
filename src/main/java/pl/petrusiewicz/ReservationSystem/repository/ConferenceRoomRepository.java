package pl.petrusiewicz.ReservationSystem.repository;

import org.springframework.data.repository.CrudRepository;
import pl.petrusiewicz.ReservationSystem.model.ConferenceRoom;

public interface ConferenceRoomRepository extends CrudRepository<ConferenceRoom, Integer> {
}
