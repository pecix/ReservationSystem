package pl.petrusiewicz.ReservationSystem.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.List;

@Data
@Entity
public class ConferenceRoom {

    @Id
    @GeneratedValue
    private int id;
    @NotNull
    @NotBlank
    @Size(min = 2, max = 20)
    private String name;
    @NotBlank
    @Size(min = 2, max = 20)
    private String description;
    @Min(0)
    @Max(10)
    private int floor;
//    private boolean available = true;
    private int numberOfSeats;
    private int numberOfStandingPlaces;
    private int numberOfLyingPlaces;
    private int numberOfHangingPlaces;
    private String projectorName;
    private boolean haveTelephone;
    @OneToOne
    private Telephone telephone;
    @OneToMany
    private List<Reservation> reservations;

}
