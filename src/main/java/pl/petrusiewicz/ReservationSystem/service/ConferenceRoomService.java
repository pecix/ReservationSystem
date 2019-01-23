package pl.petrusiewicz.ReservationSystem.service;

import org.springframework.stereotype.Service;
import pl.petrusiewicz.ReservationSystem.entity.ConferenceRoomEntity;
import pl.petrusiewicz.ReservationSystem.model.ConferenceRoom;
import pl.petrusiewicz.ReservationSystem.repository.ConferenceRoomRepository;
import pl.petrusiewicz.ReservationSystem.repository.OrganizationRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConferenceRoomService {

    private final OrganizationRepository organizationRepository;
    private  final ConferenceRoomRepository conferenceRoomRepository;

    public ConferenceRoomService(OrganizationRepository organizationRepository, ConferenceRoomRepository conferenceRoomRepository){
        this.organizationRepository = organizationRepository;
        this.conferenceRoomRepository = conferenceRoomRepository;
    }


    public List<ConferenceRoomEntity> findAll(int organizationId) {
        return organizationRepository.findById(organizationId).getConferenceRooms();
    }

    public ConferenceRoomEntity findById(int id) {
        return conferenceRoomRepository.findById(id);
    }

    public ConferenceRoomEntity findByIdOrganizationAndIdRoom(int organizationId, int id){
        var rooms = findAll(organizationId);
        var room = rooms.stream()
                .filter(r -> r.getId() == id)
                .findAny();
        return room.orElse(null);
    }

    public ConferenceRoomEntity findByName(int organizationId, String conferenceRoomName) {
        var conferenceRooms = findAll(organizationId);
        var conferenceRoom = conferenceRooms.stream()
                .filter(room -> room.getName().equalsIgnoreCase(conferenceRoomName))
                .findAny();
        return conferenceRoom.orElse(null);
    }

    public boolean existById(int id) {
        return conferenceRoomRepository.existsById(id);
    }

    public boolean existByName(int organizationId, String conferenceRoomName) {
        var conferenceRoom = findByName(organizationId, conferenceRoomName);
        return conferenceRoom != null;
    }

    public ConferenceRoomEntity addConferenceRoom(int organizationId, ConferenceRoom conferenceRoom) {
        var organization = organizationRepository.findById(organizationId);
        var conferenceRoomEntity = conferenceRoom.convertToEntity();
        conferenceRoomRepository.save(conferenceRoomEntity);
        organization.getConferenceRooms().add(conferenceRoomEntity);
        organizationRepository.save(organization);
        return conferenceRoomEntity;
    }

    public void remove(int organizationId, int id) {
        var organization = organizationRepository.findById(organizationId);
        var conferenceRoom = findById(id);
        organization.getConferenceRooms().remove(conferenceRoom);
        conferenceRoomRepository.delete(conferenceRoom);
        organizationRepository.save(organization);
    }

    public void removeAll(int organizationId) {
        var organization = organizationRepository.findById(organizationId);
        var idList = new ArrayList<Integer>();
        organization.getConferenceRooms().forEach(room -> idList.add(room.getId()));
        organization.getConferenceRooms().clear();
        organizationRepository.save(organization);
        idList.forEach(conferenceRoomRepository::deleteById);
    }

    public void update(int id, ConferenceRoom newConferenceRoom) {
        var conferenceRoom = findById(id);
        if (conferenceRoom != null) {
            conferenceRoom.setName(newConferenceRoom.getName());
            conferenceRoom.setDescription(newConferenceRoom.getDescription());
            conferenceRoom.setFloor(newConferenceRoom.getFloor());
            conferenceRoom.setNumberOfSeats(newConferenceRoom.getNumberOfSeats());
            conferenceRoom.setNumberOfStandingPlaces(newConferenceRoom.getNumberOfStandingPlaces());
            conferenceRoom.setNumberOfLyingPlaces(newConferenceRoom.getNumberOfLyingPlaces());
            conferenceRoom.setNumberOfHangingPlaces(newConferenceRoom.getNumberOfHangingPlaces());
            conferenceRoom.setProjectorName(newConferenceRoom.getProjectorName());
            conferenceRoomRepository.save(conferenceRoom);
        }
    }

}
