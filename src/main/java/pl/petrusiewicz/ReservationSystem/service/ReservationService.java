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
import java.util.Optional;
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

    public int getMinBookingTime() {
        return minBookingTime;
    }

    public int getMaxBookingTime() {
        return maxBookingTime;
    }

    public List<ReservationEntity> getAll(int roomId) {
        var conferenceRoom = conferenceRoomRepository.getById(roomId);
        return conferenceRoom.isPresent()
                ? conferenceRoom.get().getReservations()
                : new ArrayList<>();
    }

    public Optional<ReservationEntity> getById(int id) {
        return reservationRepository.getById(id);
    }

    public List<ReservationEntity> getAllByName(int roomId, String reservingName) {
        var reservations = getAll(roomId);
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

    public Optional<ReservationEntity> book(int roomId, Reservation reservation) {
        var conferenceRoom = conferenceRoomRepository.getById(roomId);
        if (conferenceRoom.isPresent()){
            var begin = reservation.getBeginReservation().truncatedTo(ChronoUnit.MINUTES);
            var end = reservation.getEndReservation().truncatedTo(ChronoUnit.MINUTES);
            reservation.setBeginReservation(begin);
            reservation.setEndReservation(end);
            var reservationEntity = reservation.convertToEntity();
            reservationRepository.save(reservationEntity);
            conferenceRoom.get().getReservations().add(reservationEntity);
            conferenceRoomRepository.save(conferenceRoom.get());
            return Optional.ofNullable(reservationEntity);
        }
        return Optional.empty();
    }

    public void cancelAllByName(int roomId, String reservingName) {
        var conferenceRoom = conferenceRoomRepository.getById(roomId);
        if(conferenceRoom.isPresent()){
            var reservations = getAllByName(roomId, reservingName);
            conferenceRoom.get().getReservations().removeAll(reservations);
            conferenceRoomRepository.save(conferenceRoom.get());
            reservationRepository.deleteAll(reservations);
        }
    }

    public void cancelById(int roomId, int id) {
        var conferenceRoom = conferenceRoomRepository.getById(roomId);
        var reservation = reservationRepository.getById(id);
        if (conferenceRoom.isPresent() && reservation.isPresent()){
            conferenceRoom.get().getReservations().remove(reservation.get());
            reservationRepository.deleteById(id);
            conferenceRoomRepository.save(conferenceRoom.get());
        }
    }

    public void cancelAll(int roomId) {
        var conferenceRoom = conferenceRoomRepository.getById(roomId);
        if (conferenceRoom.isPresent()){
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

    public boolean checkAvailability(int roomId, Reservation reservation) {
        var reservations = getAll(roomId);
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
