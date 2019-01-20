package pl.petrusiewicz.ReservationSystem.model;

import lombok.Data;
import pl.petrusiewicz.ReservationSystem.entity.ReservationEntity;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data
public class Reservation {

    @NotNull
    @Size(min = 2, max = 20)
    @NotBlank
    private String reservingName;
    @FutureOrPresent
    private LocalDateTime beginReservation;
    @Future
    private LocalDateTime endReservation;

    public ReservationEntity convertToEntity() {
        var reservationEntity = new ReservationEntity();
        reservationEntity.setReservingName(reservingName);
        reservationEntity.setBeginReservation(beginReservation);
        reservationEntity.setEndReservation(endReservation);
        return reservationEntity;
    }

}
