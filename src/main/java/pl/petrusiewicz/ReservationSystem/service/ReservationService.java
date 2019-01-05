package pl.petrusiewicz.ReservationSystem.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.petrusiewicz.ReservationSystem.model.ConferenceRoom;
import pl.petrusiewicz.ReservationSystem.model.Reservation;
import pl.petrusiewicz.ReservationSystem.repository.ConferenceRoomRepository;
import pl.petrusiewicz.ReservationSystem.repository.ReservationRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    ConferenceRoomRepository conferenceRoomRepository;


    public List<Reservation> findAll(int roomId){
        return conferenceRoomRepository.findById(roomId).getReservations();
    }

    public Reservation findById(int id){
        return reservationRepository.findById(id);
    }

    public Reservation findFirstByName(int roomId, String reservingName){
        List<Reservation> reservations = findAll(roomId);
        for(Reservation res: reservations){
            if (res.getReservingName().equalsIgnoreCase(reservingName)){
                return res;
            }
        }
        return null;
    }

    public List<Reservation> findAllByName(int roomId, String reservingName){
        List<Reservation> reservations = findAll(roomId);
        List<Reservation> temp = new ArrayList<>();
        for (Reservation reservation: reservations){
            if (reservation.getReservingName().equalsIgnoreCase(reservingName)){
                temp.add(reservation);
            }
        }
        return temp;
    }

    public void book(int roomId, Reservation reservation){
        ConferenceRoom conferenceRoom = conferenceRoomRepository.findById(roomId);
        reservationRepository.save(reservation);
        conferenceRoom.getReservations().add(reservation);
//        conferenceRoom.setAvailable(false);
        conferenceRoomRepository.save(conferenceRoom);
    }

    public void cancelAllByName(int roomId, String reservingName){
        ConferenceRoom conferenceRoom = conferenceRoomRepository.findById(roomId);
        Reservation reservation = findFirstByName(roomId, reservingName);
        while (reservation != null){
            conferenceRoom.getReservations().remove(reservation);
            reservationRepository.deleteById(reservation.getId());
            reservation = findFirstByName(roomId, reservingName);
        }
//        conferenceRoom.setAvailable(true);
        conferenceRoomRepository.save(conferenceRoom);
    }

    public void cancelById(int roomId, int id){
        ConferenceRoom conferenceRoom = conferenceRoomRepository.findById(roomId);
        conferenceRoom.getReservations().remove(reservationRepository.findById(id));
//        conferenceRoom.setAvailable(true);
        reservationRepository.deleteById(id);
        conferenceRoomRepository.save(conferenceRoom);
    }

    public void cancelAll(int roomId){
        ConferenceRoom room = conferenceRoomRepository.findById(roomId);
        List<Integer> idList = new ArrayList<>();
        for (Reservation reservation: room.getReservations()){
            idList.add(reservation.getId());
        }
        room.getReservations().clear();
        conferenceRoomRepository.save(room);
        for (Integer id: idList){
            reservationRepository.deleteById(id);
        }
    }

    public void update(int id, Reservation updatedReservation){
        Reservation reservation = findById(id);
        if (reservation != null) {
            reservation.setReservingName(updatedReservation.getReservingName());
            reservation.setBeginReservation(updatedReservation.getBeginReservation());
            reservation.setEndReservation(updatedReservation.getEndReservation());
            reservationRepository.save(reservation);
        }
    }

}
