package pl.petrusiewicz.ReservationSystem.service;

import org.springframework.stereotype.Service;
import pl.petrusiewicz.ReservationSystem.entity.ConferenceRoomEntity;
import pl.petrusiewicz.ReservationSystem.entity.TelephoneEntity;
import pl.petrusiewicz.ReservationSystem.model.Telephone;
import pl.petrusiewicz.ReservationSystem.repository.ConferenceRoomRepository;
import pl.petrusiewicz.ReservationSystem.repository.TelephoneRepository;

import java.util.Optional;


@Service
public class TelephoneService {

    private final ConferenceRoomRepository conferenceRoomRepository;
    private final TelephoneRepository telephoneRepository;

    public TelephoneService(ConferenceRoomRepository conferenceRoomRepository, TelephoneRepository telephoneRepository){
        this.conferenceRoomRepository = conferenceRoomRepository;
        this.telephoneRepository = telephoneRepository;
    }

    public Optional<TelephoneEntity> get(int roomId) {
        var conferenceRoom = conferenceRoomRepository.getById(roomId);
        return conferenceRoom.map(ConferenceRoomEntity::getTelephone);
    }

    public Optional<TelephoneEntity> add(int roomId, Telephone telephone) {
        var conferenceRoom = conferenceRoomRepository.getById(roomId);
        if (conferenceRoom.isPresent()){
            var telephoneEntity = telephone.convertToEntity();
            telephoneRepository.save(telephoneEntity);
            conferenceRoom.get().setHasTelephone(true);
            conferenceRoom.get().setTelephone(telephoneEntity);
            conferenceRoomRepository.save(conferenceRoom.get());
            return Optional.ofNullable(telephoneEntity);
        }
        return Optional.empty();
    }

    public void remove(int roomId) {
        var conferenceRoom = conferenceRoomRepository.getById(roomId);
        if (conferenceRoom.isPresent()){
            int id = conferenceRoom.get().getTelephone().getId();
            conferenceRoom.get().setHasTelephone(false);
            conferenceRoom.get().setTelephone(null);
            telephoneRepository.deleteById(id);
            conferenceRoomRepository.save(conferenceRoom.get());
        }
    }

    public void update(int roomId, Telephone updateTelephone) {
        var telephone = get(roomId);
        if (telephone.isPresent()) {
            telephone.get().setInternalNumber(updateTelephone.getInternalNumber());
            telephone.get().setExternalNumber(updateTelephone.getExternalNumber());
            telephone.get().setTelephoneConnectionInterface(updateTelephone.getTelephoneConnectionInterface());
            telephoneRepository.save(telephone.get());
        }
    }
}
