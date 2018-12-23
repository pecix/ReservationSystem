package pl.petrusiewicz.ReservationSystem.model;

public class ConferenceRoom {

    private float id;
    private String name;
    private int floor;
    private boolean available;
    private int numberOfSeats;
    private int numberOfStandingPlaces;
    private int numberOfLyingPlaces;
    private int numberOfHangingPlaces;
    private String projectorName;
    private boolean hasTelephone;
    private Telephone telephone;

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

    public boolean isHasTelephone() {
        return hasTelephone;
    }

    public void setHasTelephone(boolean hasTelephone) {
        this.hasTelephone = hasTelephone;
    }

    public Telephone getTelephone() {
        return telephone;
    }

    public void setTelephone(Telephone telephone) {
        this.telephone = telephone;
    }
}
