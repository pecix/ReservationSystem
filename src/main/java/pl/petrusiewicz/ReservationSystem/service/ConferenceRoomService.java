package pl.petrusiewicz.ReservationSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.petrusiewicz.ReservationSystem.model.ConferenceRoom;
import pl.petrusiewicz.ReservationSystem.model.Organization;
import pl.petrusiewicz.ReservationSystem.repository.ConferenceRoomRepository;
import pl.petrusiewicz.ReservationSystem.repository.OrganizationRepository;

import java.util.List;

@Service
public class ConferenceRoomService {

    @Autowired
    OrganizationRepository organizationRepository;
    @Autowired
    ConferenceRoomRepository conferenceRoomRepository;
    @Autowired
    OrganizationService organizationService;

    public List<ConferenceRoom> getAll(String organizationName){

        return organizationService.findByName(organizationName).getConferenceRooms();
    }

    public ConferenceRoom getById(int id){
        if(conferenceRoomRepository.findById(id).isPresent()){
            return conferenceRoomRepository.findById(id).get();
        } else {
            return null;
        }
    }

    public ConferenceRoom findByName(String organizationName, String conferenceRoomName){
        List<ConferenceRoom> conferenceRooms = getAll(organizationName);
        for (ConferenceRoom room: conferenceRooms){
            if(room.getName().equalsIgnoreCase(conferenceRoomName)){
                return room;
            }
        }
        return null;
    }

    public boolean isExist(String organizationName, ConferenceRoom conferenceRoom){
        List<ConferenceRoom> conferenceRooms = getAll(organizationName);
        boolean exist = false;
        for (ConferenceRoom room: conferenceRooms){
            if(!exist && room.getName().equalsIgnoreCase(conferenceRoom.getName())){
                exist=true;
            }
        }
        return exist;
    }

    public void addConferenceRoom(String organizationName, ConferenceRoom conferenceRoom){
        Organization organization = organizationService.findByName(organizationName);
        conferenceRoomRepository.save(conferenceRoom);
        organization.getConferenceRooms().add(conferenceRoom);
        organizationRepository.save(organization);
    }

    public void removeConferenceRoomByName(String organizationName, String conferenceRoomName){
        Organization organization = organizationService.findByName(organizationName);
        ConferenceRoom conferenceRoom = findByName(organizationName, conferenceRoomName);
        organization.getConferenceRooms().remove(conferenceRoom);
        conferenceRoomRepository.delete(conferenceRoom);
        organizationRepository.save(organization);
    }

    public void update(String organizationName, String conferenceRoomName, ConferenceRoom newConferenceRoom){
        ConferenceRoom conferenceRoom = findByName(organizationName, conferenceRoomName);
        if (conferenceRoom != null) {
            conferenceRoom.setName(newConferenceRoom.getName());
            conferenceRoom.setDescription(newConferenceRoom.getDescription());
            conferenceRoom.setFloor(newConferenceRoom.getFloor());
            conferenceRoom.setAvailable(newConferenceRoom.isAvailable());
            conferenceRoom.setNumberOfSeats(newConferenceRoom.getNumberOfSeats());
            conferenceRoom.setNumberOfStandingPlaces(newConferenceRoom.getNumberOfStandingPlaces());
            conferenceRoom.setNumberOfLyingPlaces(newConferenceRoom.getNumberOfLyingPlaces());
            conferenceRoom.setNumberOfHangingPlaces(newConferenceRoom.getNumberOfHangingPlaces());
            conferenceRoom.setProjectorName(newConferenceRoom.getProjectorName());
            conferenceRoomRepository.save(conferenceRoom);
        }
    }

}
