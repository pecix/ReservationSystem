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
    @Autowired
    ConferenceRoomService conferenceRoomService;

    public List<Reservation> getAll(String organizationName, String conferenceRoomName){
        return conferenceRoomService.findByName(organizationName, conferenceRoomName).getReservations();
    }

    public Reservation getById(int id){
        if (reservationRepository.findById(id).isPresent()){
            return reservationRepository.findById(id).get();
        } else {
            return null;
        }
    }

    public Reservation findByName(String organizationName, String conferenceRoomName, String reservingName){
        List<Reservation> reservations = getAll(organizationName, conferenceRoomName);
        for(Reservation res: reservations){
            if (res.getReservingName().equalsIgnoreCase(reservingName)){
                return res;
            }
        }
        return null;
    }

    public void book(String organizationName, String conferenceRoomName, Reservation reservation){
        ConferenceRoom conferenceRoom = conferenceRoomService.findByName(organizationName, conferenceRoomName);
        reservationRepository.save(reservation);
        conferenceRoom.getReservations().add(reservation);
//        conferenceRoom.setAvailable(false);
        conferenceRoomRepository.save(conferenceRoom);
    }

    public void cancelAllByName(String organizationName, String conferenceRoomName, String reservingName){
        ConferenceRoom conferenceRoom = conferenceRoomService.findByName(organizationName, conferenceRoomName);
        Reservation reservation = findByName(organizationName, conferenceRoomName, reservingName);
        while (reservation != null){
            conferenceRoom.getReservations().remove(reservation);
            reservationRepository.deleteById(reservation.getId());
            reservation = findByName(organizationName, conferenceRoomName, reservingName);
        }
//        conferenceRoom.setAvailable(true);
        conferenceRoomRepository.save(conferenceRoom);
    }

    public void cancelById(String organizationName, String conferenceRoomName, int id){
        ConferenceRoom conferenceRoom = conferenceRoomService.findByName(organizationName, conferenceRoomName);
        conferenceRoom.getReservations().remove(reservationRepository.findById(id).get());
//        conferenceRoom.setAvailable(true);
        reservationRepository.deleteById(id);
        conferenceRoomRepository.save(conferenceRoom);
    }

}
