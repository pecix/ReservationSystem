package pl.petrusiewicz.ReservationSystem.service;

import org.springframework.stereotype.Service;
import pl.petrusiewicz.ReservationSystem.entity.ConferenceRoomEntity;
import pl.petrusiewicz.ReservationSystem.model.ConferenceRoom;
import pl.petrusiewicz.ReservationSystem.repository.ConferenceRoomRepository;
import pl.petrusiewicz.ReservationSystem.repository.OrganizationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConferenceRoomService {

    private final OrganizationRepository organizationRepository;
    private  final ConferenceRoomRepository conferenceRoomRepository;

    public ConferenceRoomService(OrganizationRepository organizationRepository, ConferenceRoomRepository conferenceRoomRepository){
        this.organizationRepository = organizationRepository;
        this.conferenceRoomRepository = conferenceRoomRepository;
    }

    public List<ConferenceRoomEntity> getAll(int organizationId) {
        var organization = organizationRepository.getById(organizationId);
        return organization.isPresent()
                ? organization.get().getConferenceRooms()
                : new ArrayList<>();
    }

    public Optional<ConferenceRoomEntity> getById(int id) {
        return conferenceRoomRepository.getById(id);
    }

    public Optional<ConferenceRoomEntity> getByName(int organizationId, String conferenceRoomName) {
        var conferenceRooms = getAll(organizationId);
        return conferenceRooms.stream()
                .filter(room -> room.getName().equalsIgnoreCase(conferenceRoomName))
                .findAny();
    }

    public Optional<ConferenceRoomEntity> addConferenceRoom(int organizationId, ConferenceRoom conferenceRoom) {
        var organization = organizationRepository.getById(organizationId);
        if (organization.isPresent()){
            var conferenceRoomEntity = conferenceRoom.convertToEntity();
            conferenceRoomRepository.save(conferenceRoomEntity);
            organization.get().getConferenceRooms().add(conferenceRoomEntity);
            organizationRepository.save(organization.get());
            return Optional.ofNullable(conferenceRoomEntity);
        }
        return Optional.empty();
    }

    public void remove(int organizationId, int id) {
        var organization = organizationRepository.getById(organizationId);
        var conferenceRoom = getById(id);
        if (organization.isPresent() && conferenceRoom.isPresent()){
            organization.get().getConferenceRooms().remove(conferenceRoom.get());
            conferenceRoomRepository.delete(conferenceRoom.get());
            organizationRepository.save(organization.get());
        }
    }

    public void removeAll(int organizationId) {
        var organization = organizationRepository.getById(organizationId);
        if (organization.isPresent()){
            var idList = new ArrayList<Integer>();
            organization.get().getConferenceRooms().forEach(room -> idList.add(room.getId()));
            organization.get().getConferenceRooms().clear();
            organizationRepository.save(organization.get());
            idList.forEach(conferenceRoomRepository::deleteById);
        }
    }

    public void update(int id, ConferenceRoom newConferenceRoom) {
        var conferenceRoom = getById(id);
        if (conferenceRoom.isPresent()) {
            conferenceRoom.get().setName(newConferenceRoom.getName());
            conferenceRoom.get().setDescription(newConferenceRoom.getDescription());
            conferenceRoom.get().setFloor(newConferenceRoom.getFloor());
            conferenceRoom.get().setNumberOfSeats(newConferenceRoom.getNumberOfSeats());
            conferenceRoom.get().setNumberOfStandingPlaces(newConferenceRoom.getNumberOfStandingPlaces());
            conferenceRoom.get().setNumberOfLyingPlaces(newConferenceRoom.getNumberOfLyingPlaces());
            conferenceRoom.get().setNumberOfHangingPlaces(newConferenceRoom.getNumberOfHangingPlaces());
            conferenceRoom.get().setProjectorName(newConferenceRoom.getProjectorName());
            conferenceRoomRepository.save(conferenceRoom.get());
        }
    }
}
