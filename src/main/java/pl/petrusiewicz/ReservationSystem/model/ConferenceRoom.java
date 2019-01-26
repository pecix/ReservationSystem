package pl.petrusiewicz.ReservationSystem.model;

import lombok.Data;
import pl.petrusiewicz.ReservationSystem.entity.ConferenceRoomEntity;

import javax.validation.constraints.*;

@Data
public class ConferenceRoom {

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
    private int numberOfSeats;
    private int numberOfStandingPlaces;
    private int numberOfLyingPlaces;
    private int numberOfHangingPlaces;
    private String projectorName;
    private boolean haveTelephone;

    public ConferenceRoomEntity convertToEntity() {
        var conferenceRoomEntity = new ConferenceRoomEntity();
        conferenceRoomEntity.setName(name);
        conferenceRoomEntity.setDescription(description);
        conferenceRoomEntity.setFloor(floor);
        conferenceRoomEntity.setNumberOfSeats(numberOfSeats);
        conferenceRoomEntity.setNumberOfStandingPlaces(numberOfStandingPlaces);
        conferenceRoomEntity.setNumberOfLyingPlaces(numberOfLyingPlaces);
        conferenceRoomEntity.setNumberOfHangingPlaces(numberOfHangingPlaces);
        conferenceRoomEntity.setProjectorName(projectorName);
        conferenceRoomEntity.setHasTelephone(haveTelephone);
        return conferenceRoomEntity;
    }
}
