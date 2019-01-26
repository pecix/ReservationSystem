package pl.petrusiewicz.ReservationSystem.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class ConferenceRoomEntity {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String description;
    private Integer floor;
    private Integer numberOfSeats;
    private Integer numberOfStandingPlaces;
    private Integer numberOfLyingPlaces;
    private Integer numberOfHangingPlaces;
    private String projectorName;
    private Boolean hasTelephone;
    @OneToOne
    private TelephoneEntity telephone;
    @OneToMany
    private List<ReservationEntity> reservations;

}
