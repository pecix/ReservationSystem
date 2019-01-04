package pl.petrusiewicz.ReservationSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.petrusiewicz.ReservationSystem.model.ConferenceRoom;
import pl.petrusiewicz.ReservationSystem.model.Telephone;
import pl.petrusiewicz.ReservationSystem.repository.ConferenceRoomRepository;
import pl.petrusiewicz.ReservationSystem.repository.TelephoneRepository;


@Service
public class TelephoneService {

    @Autowired
    TelephoneRepository telephoneRepository;
    @Autowired
    ConferenceRoomRepository conferenceRoomRepository;

    public Telephone get(int roomId){
        return conferenceRoomRepository.findById(roomId).getTelephone();
    }

    public void add(int roomId, Telephone telephone){
        telephoneRepository.save(telephone);
        ConferenceRoom conferenceRoom = conferenceRoomRepository.findById(roomId);
        conferenceRoom.setHaveTelephone(true);
        conferenceRoom.setTelephone(telephone);
        conferenceRoomRepository.save(conferenceRoom);
    }

    public void remove(int roomId){
        ConferenceRoom conferenceRoom = conferenceRoomRepository.findById(roomId);
        int id = conferenceRoom.getTelephone().getId();
        conferenceRoom.setHaveTelephone(false);
        conferenceRoom.setTelephone(null);
        telephoneRepository.deleteById(id);
        conferenceRoomRepository.save(conferenceRoom);
    }

    public void update(int roomId, Telephone updateTelephone){
        Telephone telephone = conferenceRoomRepository.findById(roomId).getTelephone();
        if (telephone != null) {
            telephone.setInternalNumber(updateTelephone.getInternalNumber());
            telephone.setExternalNumber(updateTelephone.getExternalNumber());
            telephone.setTelephoneConnectionInterface(updateTelephone.getTelephoneConnectionInterface());
            telephoneRepository.save(telephone);
        }
    }
}
