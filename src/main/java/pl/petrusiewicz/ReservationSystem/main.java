package pl.petrusiewicz.ReservationSystem;

import pl.petrusiewicz.ReservationSystem.model.Organization;
import pl.petrusiewicz.ReservationSystem.model.Telephone;
import pl.petrusiewicz.ReservationSystem.repository.ConferenceRoomRepository;
import pl.petrusiewicz.ReservationSystem.repository.OrganizationRepository;
import pl.petrusiewicz.ReservationSystem.repository.TelephoneRepository;

public class main {
    public static void main(String[] args) {

        OrganizationRepository organizations = new OrganizationRepository();
        ConferenceRoomRepository conferenceRooms = new ConferenceRoomRepository();
        TelephoneRepository telephones = new TelephoneRepository();

        Organization pepsi = new Organization();
        pepsi.setName("pepsi");
        organizations.add(pepsi);
        System.out.println(organizations.get(0).getName());



    }
}
