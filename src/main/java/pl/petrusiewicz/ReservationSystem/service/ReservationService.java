package pl.petrusiewicz.ReservationSystem.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.petrusiewicz.ReservationSystem.entity.ReservationEntity;
import pl.petrusiewicz.ReservationSystem.model.Reservation;
import pl.petrusiewicz.ReservationSystem.repository.ConferenceRoomRepository;
import pl.petrusiewicz.ReservationSystem.repository.ReservationRepository;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private final ConferenceRoomRepository conferenceRoomRepository;
    private final ReservationRepository reservationRepository;

    public ReservationService(ConferenceRoomRepository conferenceRoomRepository, ReservationRepository reservationRepository){
        this.conferenceRoomRepository = conferenceRoomRepository;
        this.reservationRepository = reservationRepository;
    }

    @Value("${reservationService.minBookingTime}")
    private int minBookingTime;
    @Value("${reservationService.maxBookingTime}")
    private int maxBookingTime;

    public List<ReservationEntity> findAll(int roomId) {
        return conferenceRoomRepository.findById(roomId).getReservations();
    }

    public ReservationEntity findById(int id) {
        return reservationRepository.findById(id);
    }

    public List<ReservationEntity> findAllByName(int roomId, String reservingName) {
        var reservations = findAll(roomId);
        return reservations.stream()
                .filter(res -> res.getReservingName().equalsIgnoreCase(reservingName))
                .collect(Collectors.toList());
    }

    public boolean checkTimeRestriction(Reservation reservation) {
        var begin = reservation.getBeginReservation().truncatedTo(ChronoUnit.MINUTES);
        var end = reservation.getEndReservation().truncatedTo(ChronoUnit.MINUTES);
        var minutesBetween = ChronoUnit.MINUTES.between(begin, end);
        return begin.isBefore(end) && minutesBetween >= minBookingTime && minutesBetween <= maxBookingTime;
    }

    public ReservationEntity book(int roomId, Reservation reservation) {
        var begin = reservation.getBeginReservation().truncatedTo(ChronoUnit.MINUTES);
        var end = reservation.getEndReservation().truncatedTo(ChronoUnit.MINUTES);
        reservation.setBeginReservation(begin);
        reservation.setEndReservation(end);
        var conferenceRoom = conferenceRoomRepository.findById(roomId);
        var reservationEntity = reservation.convertToEntity();
        reservationRepository.save(reservationEntity);
        conferenceRoom.getReservations().add(reservationEntity);
        conferenceRoomRepository.save(conferenceRoom);
        return reservationEntity;
    }

    public void cancelAllByName(int roomId, String reservingName) {
        var conferenceRoom = conferenceRoomRepository.findById(roomId);
        var reservations = findAllByName(roomId, reservingName);
        conferenceRoom.getReservations().removeAll(reservations);
        conferenceRoomRepository.save(conferenceRoom);
        reservationRepository.deleteAll(reservations);
    }

    public void cancelById(int roomId, int id) {
        var conferenceRoom = conferenceRoomRepository.findById(roomId);
        conferenceRoom.getReservations().remove(reservationRepository.findById(id));
        reservationRepository.deleteById(id);
        conferenceRoomRepository.save(conferenceRoom);
    }

    public void cancelAll(int roomId) {
        var room = conferenceRoomRepository.findById(roomId);
        var idList = new ArrayList<Integer>();
        room.getReservations().forEach(res -> idList.add(res.getId()));
        room.getReservations().clear();
        conferenceRoomRepository.save(room);
        idList.forEach(reservationRepository::deleteById);
    }

    public void update(int id, Reservation updatedReservation) {
        var reservation = findById(id);
        if (reservation != null) {
            reservation.setReservingName(updatedReservation.getReservingName());
            reservation.setBeginReservation(updatedReservation.getBeginReservation());
            reservation.setEndReservation(updatedReservation.getEndReservation());
            reservationRepository.save(reservation);
        }
    }

    public boolean checkAvailability(int roomId, Reservation reservation) {
        var reservations = findAll(roomId);
        var begin = reservation.getBeginReservation().truncatedTo(ChronoUnit.MINUTES);
        var end = reservation.getEndReservation().truncatedTo(ChronoUnit.MINUTES);
        Predicate<ReservationEntity> predicate = res -> begin.isEqual(res.getBeginReservation()) || begin.isEqual(res.getEndReservation()) ||
                end.isEqual(res.getBeginReservation()) || end.isEqual(res.getEndReservation()) ||
                begin.isAfter(res.getBeginReservation()) && begin.isBefore(res.getEndReservation()) ||
                end.isAfter(res.getBeginReservation()) && end.isBefore(res.getEndReservation());
        return reservations.stream().noneMatch(predicate);
    }

    public List<ReservationEntity> sort(List<ReservationEntity> reservations) {
        reservations.sort(Comparator.comparing(ReservationEntity::getBeginReservation));
        return reservations;
    }

}
