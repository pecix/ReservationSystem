package pl.petrusiewicz.ReservationSystem.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.List;

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
    private boolean available = true;
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

    //=======================================================


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public int getNumberOfStandingPlaces() {
        return numberOfStandingPlaces;
    }

    public void setNumberOfStandingPlaces(int numberOfStandingPlaces) {
        this.numberOfStandingPlaces = numberOfStandingPlaces;
    }

    public int getNumberOfLyingPlaces() {
        return numberOfLyingPlaces;
    }

    public void setNumberOfLyingPlaces(int numberOfLyingPlaces) {
        this.numberOfLyingPlaces = numberOfLyingPlaces;
    }

    public int getNumberOfHangingPlaces() {
        return numberOfHangingPlaces;
    }

    public void setNumberOfHangingPlaces(int numberOfHangingPlaces) {
        this.numberOfHangingPlaces = numberOfHangingPlaces;
    }

    public String getProjectorName() {
        return projectorName;
    }

    public void setProjectorName(String projectorName) {
        this.projectorName = projectorName;
    }

    public boolean isHaveTelephone() {
        return haveTelephone;
    }

    public void setHaveTelephone(boolean haveTelephone) {
        this.haveTelephone = haveTelephone;
    }

    public Telephone getTelephone() {
        return telephone;
    }

    public void setTelephone(Telephone telephone) {
        this.telephone = telephone;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    //===============================================================================================


    @Override
    public String toString() {
        return "ConferenceRoom{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", floor=" + floor +
                ", available=" + available +
                ", numberOfSeats=" + numberOfSeats +
                ", numberOfStandingPlaces=" + numberOfStandingPlaces +
                ", numberOfLyingPlaces=" + numberOfLyingPlaces +
                ", numberOfHangingPlaces=" + numberOfHangingPlaces +
                ", projectorName='" + projectorName + '\'' +
                ", haveTelephone=" + haveTelephone +
                ", telephone=" + telephone +
                ", reservations=" + reservations +
                '}';
    }
}
