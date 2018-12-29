package pl.petrusiewicz.ReservationSystem.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Reservation {

    @Id
    @GeneratedValue
    private int id;
    private String reservingName;
    private LocalDateTime beginReservation;
    private LocalDateTime endReservation;

    //===============================================


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReservingName() {
        return reservingName;
    }

    public void setReservingName(String reservingName) {
        this.reservingName = reservingName;
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
