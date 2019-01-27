package pl.petrusiewicz.ReservationSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.petrusiewicz.ReservationSystem.entity.ReservationEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Integer> {

    Optional<ReservationEntity> getById(int id);

    List<ReservationEntity> findAllByIdConferenceRoom(int roomId);

    List<ReservationEntity> findAllByIdConferenceRoomOrderByBeginReservation(int roomId);

    List<ReservationEntity> findAllByIdConferenceRoomAndReservingNameIgnoreCase(int roomId, String reservingName);

    boolean existsByIdConferenceRoomEqualsAndBeginReservationBetweenOrIdConferenceRoomEqualsAndEndReservationBetween(int id1, LocalDateTime begin1, LocalDateTime end1, int id2, LocalDateTime begin2, LocalDateTime end2);

}
