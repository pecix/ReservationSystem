package pl.petrusiewicz.ReservationSystem.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
public class ReservationEntity {

    @Id
    @GeneratedValue
    private Integer id;
    private String reservingName;
    private LocalDateTime beginReservation;
    private LocalDateTime endReservation;

}
