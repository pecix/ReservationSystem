package pl.petrusiewicz.ReservationSystem.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
public class OrganizationEntity {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @OneToMany
    private List<ConferenceRoomEntity> conferenceRooms;

}


