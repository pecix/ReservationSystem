package pl.petrusiewicz.ReservationSystem.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class ReservationEntity {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer idConferenceRoom;
    private String reservingName;
    private LocalDateTime beginReservation;
    private LocalDateTime endReservation;

}
