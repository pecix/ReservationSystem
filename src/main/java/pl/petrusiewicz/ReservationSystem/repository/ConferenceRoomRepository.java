package pl.petrusiewicz.ReservationSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pl.petrusiewicz.ReservationSystem.model.ConferenceRoom;

@Repository
public interface ConferenceRoomRepository extends JpaRepository<ConferenceRoom, Integer> {
    ConferenceRoom findById(int id);
}
