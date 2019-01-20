package pl.petrusiewicz.ReservationSystem.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.petrusiewicz.ReservationSystem.config.Config;
import pl.petrusiewicz.ReservationSystem.entity.ReservationEntity;
import pl.petrusiewicz.ReservationSystem.model.Reservation;
import pl.petrusiewicz.ReservationSystem.repository.ConferenceRoomRepository;
import pl.petrusiewicz.ReservationSystem.repository.ReservationRepository;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    ConferenceRoomRepository conferenceRoomRepository;

    public List<ReservationEntity> findAll(int roomId) {
        return conferenceRoomRepository.findById(roomId).getReservations();
    }

    public ReservationEntity findById(int id) {
        return reservationRepository.findById(id);
    }

    public ReservationEntity findFirstByName(int roomId, String reservingName) {
        var reservations = findAll(roomId);
        for (ReservationEntity res : reservations) {
            if (res.getReservingName().equalsIgnoreCase(reservingName)) {
                return res;
            }
        }
        return null;
    }

    public List<ReservationEntity> findAllByName(int roomId, String reservingName) {
        var reservations = findAll(roomId);
        var temp = new ArrayList<ReservationEntity>();
        for (ReservationEntity reservation : reservations) {
            if (reservation.getReservingName().equalsIgnoreCase(reservingName)) {
                temp.add(reservation);
            }
        }
        return temp;
    }

    public boolean checkTimeLimits(Reservation reservation) {
        var begin = reservation.getBeginReservation().truncatedTo(ChronoUnit.MINUTES);
        var end = reservation.getEndReservation().truncatedTo(ChronoUnit.MINUTES);
        var minutesBetween = ChronoUnit.MINUTES.between(begin, end);
        return begin.isBefore(end) && minutesBetween >= Config.MIN_BOOKING_TIME && minutesBetween <= Config.MAX_BOOKING_TIME;
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
        var reservation = findFirstByName(roomId, reservingName);
        while (reservation != null) {
            conferenceRoom.getReservations().remove(reservation);
            reservationRepository.deleteById(reservation.getId());
            reservation = findFirstByName(roomId, reservingName);
        }
        conferenceRoomRepository.save(conferenceRoom);
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
        for (ReservationEntity reservation : room.getReservations()) {
            idList.add(reservation.getId());
        }
        room.getReservations().clear();
        conferenceRoomRepository.save(room);
        for (Integer id : idList) {
            reservationRepository.deleteById(id);
        }
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
        var start = reservation.getBeginReservation();
        var end = reservation.getEndReservation();
        for (ReservationEntity res : reservations) {
            if (start.isEqual(res.getBeginReservation()) || start.isEqual(res.getEndReservation())) {
                return false;
            } else if (end.isEqual(res.getBeginReservation()) || end.isEqual(res.getEndReservation())) {
                return false;
            } else if (start.isAfter(res.getBeginReservation()) && start.isBefore(res.getEndReservation())) {
                return false;
            } else if (end.isAfter(res.getBeginReservation()) && end.isBefore(res.getEndReservation())) {
                return false;
            }
        }

        return true;
    }

    public List<ReservationEntity> sort(List<ReservationEntity> reservations) {
        var reservationsArray = new ReservationEntity[reservations.size()];
        reservationsArray = reservations.toArray(reservationsArray);
        var temp = new ReservationEntity();
        for (int i = 0; i < reservationsArray.length; i++) {
            for (int j = 1; j < reservationsArray.length; j++) {
                if (reservationsArray[j].getBeginReservation().isBefore(reservationsArray[j - 1].getBeginReservation())) {
                    temp = reservationsArray[j - 1];
                    reservationsArray[j - 1] = reservationsArray[j];
                    reservationsArray[j] = temp;
                }
            }
        }
        var sortedReservations = new ArrayList<ReservationEntity>();
        sortedReservations.addAll(Arrays.asList(reservationsArray));
        return sortedReservations;
    }

}
