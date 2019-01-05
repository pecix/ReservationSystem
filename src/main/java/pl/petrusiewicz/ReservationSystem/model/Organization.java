package pl.petrusiewicz.ReservationSystem.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

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

    //====================================================


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<ConferenceRoom> getConferenceRooms() {
        return conferenceRooms;
    }

    public void setConferenceRooms(List<ConferenceRoom> conferenceRooms) {
        this.conferenceRooms = conferenceRooms;
    }

    //=====================================================


    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", conferenceRooms=" + conferenceRooms +
                '}';
    }
}
