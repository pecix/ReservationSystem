package pl.petrusiewicz.ReservationSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.petrusiewicz.ReservationSystem.model.ConferenceRoom;
import pl.petrusiewicz.ReservationSystem.model.Organization;
import pl.petrusiewicz.ReservationSystem.repository.OrganizationRepo;

import java.util.List;

@Service
public class ConferenceRoomService {

    @Autowired
    OrganizationRepo repository;
    @Autowired
    OrganizationService service;


//    public void addConferenceRoom(String name, ConferenceRoom conferenceRoom){
//        Organization organization = service.findByName(name);
//        List<ConferenceRoom> conferenceRooms = organization.getConferenceRooms();
//        for (ConferenceRoom c: conferenceRooms){
//            if (c.getName().equalsIgnoreCase(name)){
//
//            }
//        }
//    }
}
