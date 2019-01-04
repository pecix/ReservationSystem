//package pl.petrusiewicz.ReservationSystem.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import pl.petrusiewicz.ReservationSystem.model.ConferenceRoom;
//import pl.petrusiewicz.ReservationSystem.model.Organization;
//import pl.petrusiewicz.ReservationSystem.model.Telephone;
//import pl.petrusiewicz.ReservationSystem.repository.ConferenceRoomRepository;
//import pl.petrusiewicz.ReservationSystem.repository.OrganizationRepository;
//import pl.petrusiewicz.ReservationSystem.repository.TelephoneRepository;
//
//import java.util.List;
//
//@Service
//public class TelephoneService {
//
//    @Autowired
//    TelephoneRepository telephoneRepository;
//    @Autowired
//    ConferenceRoomRepository conferenceRoomRepository;
//    @Autowired
//    ConferenceRoomService conferenceRoomService;
//
//    public Telephone get(String organizationName, String conferenceRoomName){
//        return conferenceRoomService.findByName(organizationName, conferenceRoomName).getTelephone();
//    }
//
//    public void add(String organizationName, String conferenceRoomName, Telephone telephone){
//        telephoneRepository.save(telephone);
//        ConferenceRoom conferenceRoom = conferenceRoomService.findByName(organizationName, conferenceRoomName);
//        conferenceRoom.setHaveTelephone(true);
//        conferenceRoom.setTelephone(telephone);
//        conferenceRoomRepository.save(conferenceRoom);
//    }
//
//    public void remove(String organizationName, String conferenceRoomName){
//        ConferenceRoom conferenceRoom = conferenceRoomService.findByName(organizationName, conferenceRoomName);
//        int id = conferenceRoom.getTelephone().getId();
//        conferenceRoom.setHaveTelephone(false);
//        conferenceRoom.setTelephone(null);
//        telephoneRepository.deleteById(id);
//        conferenceRoomRepository.save(conferenceRoom);
//    }
//
//    public void update(String organizationName, String conferenceRoomName, Telephone newTelephone){
//        Telephone telephone = get(organizationName, conferenceRoomName);
//        if (telephone != null) {
//            telephone.setInternalNumber(newTelephone.getInternalNumber());
//            telephone.setExternalNumber(newTelephone.getExternalNumber());
//            telephone.setTelephoneConnectionInterface(newTelephone.getTelephoneConnectionInterface());
//            telephoneRepository.save(telephone);
//        }
//    }
//}
