package pl.petrusiewicz.ReservationSystem.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Reservation {

    @Id
    @GeneratedValue
    private int id;
    @NotNull
    @Size(min = 2, max = 20)
    @NotBlank
    private String reservingName;
    @FutureOrPresent
    private LocalDateTime beginReservation;
    @Future
    private LocalDateTime endReservation;

}
