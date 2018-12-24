package pl.petrusiewicz.ReservationSystem.service;

import org.springframework.stereotype.Service;
import pl.petrusiewicz.ReservationSystem.model.ConferenceRoom;
import pl.petrusiewicz.ReservationSystem.repository.ConferenceRoomRepository;

import java.util.List;

@Service
public class ConferenceRoomService {

    ConferenceRoomRepository repository = new ConferenceRoomRepository();

    public List<ConferenceRoom> getAll(){
        return repository.getAll();
    }

    public ConferenceRoom get(int id){
        return repository.get(id);
    }

    public void  add(ConferenceRoom conferenceRoom){
        repository.add(conferenceRoom);
    }

    public void remove(int id){
        repository.remove(id);
    }

    public void update(int id, ConferenceRoom conferenceRoom){
        repository.update(id, conferenceRoom);
    }



}
