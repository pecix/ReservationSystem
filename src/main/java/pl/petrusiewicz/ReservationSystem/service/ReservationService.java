package pl.petrusiewicz.ReservationSystem.service;


import org.springframework.stereotype.Service;
import pl.petrusiewicz.ReservationSystem.config.Config;
import pl.petrusiewicz.ReservationSystem.entity.ReservationEntity;
import pl.petrusiewicz.ReservationSystem.model.Reservation;
import pl.petrusiewicz.ReservationSystem.repository.ConferenceRoomRepository;
import pl.petrusiewicz.ReservationSystem.repository.ReservationRepository;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    private final ConferenceRoomRepository conferenceRoomRepository;
    private final ReservationRepository reservationRepository;
    private final Config config;

    public ReservationService(ConferenceRoomRepository conferenceRoomRepository, ReservationRepository reservationRepository, Config config) {
        this.conferenceRoomRepository = conferenceRoomRepository;
        this.reservationRepository = reservationRepository;
        this.config = config;
    }

    public List<ReservationEntity> getAll(int roomId) {
        return reservationRepository.findAllByIdConferenceRoom(roomId);
    }

    public Optional<ReservationEntity> getById(int id) {
        return reservationRepository.getById(id);
    }

    public List<ReservationEntity> getAllByName(int roomId, String reservingName) {
        return reservationRepository.findAllByIdConferenceRoomAndReservingNameIgnoreCase(roomId, reservingName);
    }

    public boolean checkTimeRestriction(Reservation reservation) {
        var begin = reservation.getBeginReservation().truncatedTo(ChronoUnit.MINUTES);
        var end = reservation.getEndReservation().truncatedTo(ChronoUnit.MINUTES);
        var minutesBetween = ChronoUnit.MINUTES.between(begin, end);
        return begin.isBefore(end) && minutesBetween >= config.getMinBookingTime() && minutesBetween <= config.getMaxBookingTime();
    }

    public Optional<ReservationEntity> book(int roomId, Reservation reservation) {
        var conferenceRoom = conferenceRoomRepository.getById(roomId);
        if (conferenceRoom.isPresent()) {
            var begin = reservation.getBeginReservation().truncatedTo(ChronoUnit.MINUTES);
            var end = reservation.getEndReservation().truncatedTo(ChronoUnit.MINUTES);
            reservation.setBeginReservation(begin);
            reservation.setEndReservation(end);
            var reservationEntity = reservation.convertToEntity();
            reservationEntity.setIdConferenceRoom(roomId);
            reservationRepository.save(reservationEntity);
            conferenceRoom.get().getReservations().add(reservationEntity);
            conferenceRoomRepository.save(conferenceRoom.get());
            return Optional.of(reservationEntity);
        }
        return Optional.empty();
    }

    public void cancelAllByName(int roomId, String reservingName) {
        var conferenceRoom = conferenceRoomRepository.getById(roomId);
        if (conferenceRoom.isPresent()) {
            var reservations = getAllByName(roomId, reservingName);
            conferenceRoom.get().getReservations().removeAll(reservations);
            conferenceRoomRepository.save(conferenceRoom.get());
            reservationRepository.deleteAll(reservations);
        }
    }

    public void cancelById(int roomId, int id) {
        var conferenceRoom = conferenceRoomRepository.getById(roomId);
        var reservation = reservationRepository.getById(id);
        if (conferenceRoom.isPresent() && reservation.isPresent()) {
            conferenceRoom.get().getReservations().remove(reservation.get());
            reservationRepository.deleteById(id);
            conferenceRoomRepository.save(conferenceRoom.get());
        }
    }

    public void cancelAll(int roomId) {
        var conferenceRoom = conferenceRoomRepository.getById(roomId);
        if (conferenceRoom.isPresent()) {
            var idList = new ArrayList<Integer>();
            conferenceRoom.get().getReservations().forEach(res -> idList.add(res.getId()));
            conferenceRoom.get().getReservations().clear();
            conferenceRoomRepository.save(conferenceRoom.get());
            idList.forEach(reservationRepository::deleteById);
        }
    }

    public void update(int id, Reservation updatedReservation) {
        var reservation = getById(id);
        if (reservation.isPresent()) {
            reservation.get().setReservingName(updatedReservation.getReservingName());
            reservation.get().setBeginReservation(updatedReservation.getBeginReservation());
            reservation.get().setEndReservation(updatedReservation.getEndReservation());
            reservationRepository.save(reservation.get());
        }
    }

    public boolean isAlreadyReserved(int roomId, Reservation reservation) {
        return reservationRepository.existsByIdConferenceRoomEqualsAndBeginReservationBetweenOrIdConferenceRoomEqualsAndEndReservationBetween(
                roomId, reservation.getBeginReservation(), reservation.getEndReservation(),
                roomId, reservation.getBeginReservation(), reservation.getEndReservation()
        );
    }

    public List<ReservationEntity> sortByBeginReservation(int roomId) {
        return reservationRepository.findAllByIdConferenceRoomOrderByBeginReservation(roomId);
    }
}


