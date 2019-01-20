package pl.petrusiewicz.ReservationSystem.entity;

import lombok.Data;
import pl.petrusiewicz.ReservationSystem.model.TelephoneConnectionInterface;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class TelephoneEntity {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer internalNumber;
    private String externalNumber;
    private TelephoneConnectionInterface telephoneConnectionInterface;

}
