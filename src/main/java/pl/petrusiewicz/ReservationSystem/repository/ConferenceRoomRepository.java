package pl.petrusiewicz.ReservationSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.petrusiewicz.ReservationSystem.entity.ConferenceRoomEntity;

import java.util.Optional;

@Repository
public interface ConferenceRoomRepository extends JpaRepository<ConferenceRoomEntity, Integer> {

    Optional<ConferenceRoomEntity> getById(int id);

}
