package pl.petrusiewicz.ReservationSystem.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class OrganizationEntity {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @OneToMany(fetch = FetchType.LAZY)
    private List<ConferenceRoomEntity> conferenceRooms;

}


