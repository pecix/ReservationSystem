package pl.petrusiewicz.ReservationSystem.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Organization {

    @Id
    @GeneratedValue
    private int id;
    @NotNull
    @Size(min = 2, max = 20)
    @NotBlank
    private String name;
    @OneToMany
    private List<ConferenceRoom> conferenceRooms;

}


