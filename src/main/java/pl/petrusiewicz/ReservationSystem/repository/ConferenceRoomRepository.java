package pl.petrusiewicz.ReservationSystem.repository;


import pl.petrusiewicz.ReservationSystem.model.ConferenceRoom;

import java.util.ArrayList;
import java.util.List;

public class ConferenceRoomRepository {

    private List<ConferenceRoom> conferenceRooms = new ArrayList<>();

    public void add(ConferenceRoom conferenceRoom){
        conferenceRooms.add(conferenceRoom);
    }

    public List<ConferenceRoom> getAll(){
        return conferenceRooms;
    }

    public ConferenceRoom get(int id){
        return conferenceRooms.get(id);
    }

    public void remove(int id){
        conferenceRooms.remove(id);
    }

    public void update(int id, ConferenceRoom conferenceRoom){
        conferenceRooms.set(id, conferenceRoom);
    }

    public boolean isExist(String name){
        for(ConferenceRoom confRoom: conferenceRooms){
            if (confRoom.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

}
