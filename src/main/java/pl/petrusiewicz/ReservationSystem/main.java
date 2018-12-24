package pl.petrusiewicz.ReservationSystem;

import pl.petrusiewicz.ReservationSystem.model.ConferenceRoom;
import pl.petrusiewicz.ReservationSystem.model.ConnectionInterface;
import pl.petrusiewicz.ReservationSystem.model.Organization;
import pl.petrusiewicz.ReservationSystem.model.Telephone;
import pl.petrusiewicz.ReservationSystem.repository.ConferenceRoomRepository;
import pl.petrusiewicz.ReservationSystem.repository.OrganizationRepository;
import pl.petrusiewicz.ReservationSystem.repository.TelephoneRepository;

import java.sql.SQLOutput;
import java.time.LocalDateTime;

public class main {
    public static void main(String[] args) {

        OrganizationRepository organizations = new OrganizationRepository();
        ConferenceRoomRepository conferenceRooms = new ConferenceRoomRepository();
        TelephoneRepository telephones = new TelephoneRepository();

        Organization pepsi = new Organization();
        Organization cola = new Organization();
        pepsi.setName("pepsi");
        cola.setName("cola");
        organizations.add(pepsi);
        organizations.add(cola);

        Telephone blueTelephone = new Telephone();
        blueTelephone.setInternalNumber(100);
        blueTelephone.setExternalNumber("+48 5555 101");
        blueTelephone.setConnectionInterface(ConnectionInterface.USB);
        telephones.add(blueTelephone);

        ConferenceRoom blueRoom = new ConferenceRoom();
        blueRoom.setId(1);
        blueRoom.setName("Blue Room");
        blueRoom.setFloor(1);
        blueRoom.setAvailable(true);
        blueRoom.setNumberOfSeats(100);
        blueRoom.setNumberOfStandingPlaces(50);
        blueRoom.setNumberOfLyingPlaces(0);
        blueRoom.setNumberOfHangingPlaces(0);
        blueRoom.setProjectorName("Blue projector");
        blueRoom.setHaveTelephone(true);
        blueRoom.setTelephone(telephones.get(0));
        blueRoom.setNameOfBookingOrganization(organizations.get(0).getName());
        blueRoom.setBeginReservation(LocalDateTime.of(2018,12,24,10,0));
        blueRoom.setEndReservation(LocalDateTime.of(2018,12,24,12,0 ));
        conferenceRooms.add(blueRoom);

        System.out.println(organizations.isExist("pepsi"));
        System.out.println(conferenceRooms.isExist("Blue Room"));
        System.out.println(conferenceRooms.get(0).getEndReservation());



    }
}
