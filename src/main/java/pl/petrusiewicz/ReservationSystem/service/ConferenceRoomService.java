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

    public List<ConferenceRoom> findAll(int organizationId){
        return organizationService.findById(organizationId).getConferenceRooms();
    }

    public ConferenceRoom findById(int id){
        return conferenceRoomRepository.findById(id);
    }

    public ConferenceRoom findByName(int organizationId, String conferenceRoomName){
        List<ConferenceRoom> conferenceRooms = findAll(organizationId);
        for (ConferenceRoom room: conferenceRooms){
            if(room.getName().equalsIgnoreCase(conferenceRoomName)){
                return room;
            }
        }
        return null;
    }

    public boolean isExistByName(int organizationId, ConferenceRoom conferenceRoom){
        List<ConferenceRoom> conferenceRooms = findAll(organizationId);
        boolean exist = false;
        for (ConferenceRoom room: conferenceRooms){
            if(!exist && room.getName().equalsIgnoreCase(conferenceRoom.getName())){
                exist=true;
            }
        }
        return exist;
    }

    public void addConferenceRoom(int organizationId, ConferenceRoom conferenceRoom){
        Organization organization = organizationService.findById(organizationId);
        conferenceRoomRepository.save(conferenceRoom);
        organization.getConferenceRooms().add(conferenceRoom);
        organizationRepository.save(organization);
    }

    public void remove(int organizationId, int id){
        Organization organization = organizationService.findById(organizationId);
        ConferenceRoom conferenceRoom = findById(id);
        organization.getConferenceRooms().remove(conferenceRoom);
        conferenceRoomRepository.delete(conferenceRoom);
        organizationRepository.save(organization);
    }

//    public void removeConferenceRoomByName(int organizationId, String conferenceRoomName){
//        Organization organization = organizationService.findById(organizationId);
//        ConferenceRoom conferenceRoom = findByName(organizationId, conferenceRoomName);
//        organization.getConferenceRooms().remove(conferenceRoom);
//        conferenceRoomRepository.delete(conferenceRoom);
//        organizationRepository.save(organization);
//    }

    public void update(int id, ConferenceRoom newConferenceRoom){
        ConferenceRoom conferenceRoom = findById(id);
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
