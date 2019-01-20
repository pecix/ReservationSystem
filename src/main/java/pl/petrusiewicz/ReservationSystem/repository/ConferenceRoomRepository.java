package pl.petrusiewicz.ReservationSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.petrusiewicz.ReservationSystem.entity.ConferenceRoomEntity;

@Repository
public interface ConferenceRoomRepository extends JpaRepository<ConferenceRoomEntity, Integer> {

    ConferenceRoomEntity findById(int id);

}
