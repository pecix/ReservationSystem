package pl.petrusiewicz.ReservationSystem.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    @Autowired
    OrganizationService organizations;
    @Autowired
    ConferenceRoomService conferenceRooms;

//    public void book(int id, Organization organization, LocalDateTime begin, LocalDateTime end){
//        ConferenceRoom room = conferenceRooms.get(id);
//        if (room.isAvailable()){
//            room.setNameOfBookingOrganization(organization.getName());
//            room.setBeginReservation(begin);
//            room.setEndReservation(end);
//            room.setAvailable(false);
//            conferenceRooms.update(id, room);
//        }
//    }

//    public void cancel(int id, Organization organization){
//        ConferenceRoom room = conferenceRooms.get(id);
//        if(!room.isAvailable() && room.getNameOfBookingOrganization().equals(organization.getName())){
//            room.setNameOfBookingOrganization(null);
//            room.setBeginReservation(null);
//            room.setEndReservation(null);
//            room.setAvailable(true);
//            conferenceRooms.update(id, room);
//        }
//    }

}
