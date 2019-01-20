package pl.petrusiewicz.ReservationSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.petrusiewicz.ReservationSystem.entity.TelephoneEntity;
import pl.petrusiewicz.ReservationSystem.model.Telephone;
import pl.petrusiewicz.ReservationSystem.repository.ConferenceRoomRepository;
import pl.petrusiewicz.ReservationSystem.repository.TelephoneRepository;


@Service
public class TelephoneService {

    @Autowired
    TelephoneRepository telephoneRepository;
    @Autowired
    ConferenceRoomRepository conferenceRoomRepository;

    public TelephoneEntity get(int roomId) {
        return conferenceRoomRepository.findById(roomId).getTelephone();
    }

    public TelephoneEntity add(int roomId, Telephone telephone) {
        var telephoneEntity = telephone.convertToEntity();
        telephoneRepository.save(telephoneEntity);
        var conferenceRoom = conferenceRoomRepository.findById(roomId);
        conferenceRoom.setHaveTelephone(true);
        conferenceRoom.setTelephone(telephoneEntity);
        conferenceRoomRepository.save(conferenceRoom);
        return telephoneEntity;
    }

    public void remove(int roomId) {
        var conferenceRoom = conferenceRoomRepository.findById(roomId);
        int id = conferenceRoom.getTelephone().getId();
        conferenceRoom.setHaveTelephone(false);
        conferenceRoom.setTelephone(null);
        telephoneRepository.deleteById(id);
        conferenceRoomRepository.save(conferenceRoom);
    }

    public void update(int roomId, Telephone updateTelephone) {
        var telephone = conferenceRoomRepository.findById(roomId).getTelephone();
        if (telephone != null) {
            telephone.setInternalNumber(updateTelephone.getInternalNumber());
            telephone.setExternalNumber(updateTelephone.getExternalNumber());
            telephone.setTelephoneConnectionInterface(updateTelephone.getTelephoneConnectionInterface());
            telephoneRepository.save(telephone);
        }
    }
}
