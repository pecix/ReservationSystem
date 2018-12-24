package pl.petrusiewicz.ReservationSystem.model;

import java.time.LocalDateTime;

public class ConferenceRoom {

    private float id;
    private String name;
    private int floor;
    private boolean available = true;
    private int numberOfSeats;
    private int numberOfStandingPlaces;
    private int numberOfLyingPlaces;
    private int numberOfHangingPlaces;
    private String projectorName;
    private boolean haveTelephone;
    private Telephone telephone;
    private String nameOfBookingOrganization;
    private LocalDateTime beginReservation;
    private LocalDateTime endReservation;

    public float getId() {
        return id;
    }

    public void setId(float id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getNameOfBookingOrganization() {
        return nameOfBookingOrganization;
    }

    public void setNameOfBookingOrganization(String nameOfBookingOrganization) {
        this.nameOfBookingOrganization = nameOfBookingOrganization;
    }

    public LocalDateTime getBeginReservation() {
        return beginReservation;
    }

    public void setBeginReservation(LocalDateTime beginReservation) {
        this.beginReservation = beginReservation;
    }

    public LocalDateTime getEndReservation() {
        return endReservation;
    }

    public void setEndReservation(LocalDateTime endReservation) {
        this.endReservation = endReservation;
    }
}
