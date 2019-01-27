package pl.petrusiewicz.ReservationSystem.model;

import lombok.Data;
import pl.petrusiewicz.ReservationSystem.entity.ReservationEntity;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data
public class Reservation {

    @NotNull
    @NotBlank
    @Size(min = 2, max = 20, message = "min 2 characters, max 20 characters")
    private String reservingName;
    @FutureOrPresent(message = "Begin reservation must be future or present")
    private LocalDateTime beginReservation;
    @Future(message = "End reservation must be future")
    private LocalDateTime endReservation;

    public ReservationEntity convertToEntity() {
        var reservationEntity = new ReservationEntity();
        reservationEntity.setReservingName(reservingName);
        reservationEntity.setBeginReservation(beginReservation);
        reservationEntity.setEndReservation(endReservation);
        return reservationEntity;
    }
}
