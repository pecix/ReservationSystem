package pl.petrusiewicz.ReservationSystem.model;

import lombok.Data;
import pl.petrusiewicz.ReservationSystem.entity.ConferenceRoomEntity;

import javax.validation.constraints.*;

@Data
public class ConferenceRoom {

    @NotNull
    @NotBlank
    @Size(min = 2, max = 20, message = "min 2 characters, max 20 characters")
    private String name;
    @NotBlank
    @Size(min = 2, max = 20, message = "min 2 characters, max 20 characters")
    private String description;
    @Min(value = 0, message = "min value 0")
    @Max(value = 10, message = "max value 10")
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
