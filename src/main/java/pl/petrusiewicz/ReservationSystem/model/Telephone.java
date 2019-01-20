package pl.petrusiewicz.ReservationSystem.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@Entity
public class Telephone {

    @Id
    @GeneratedValue
    private int id;
    @Min(1)
    @Max(100)
    private int internalNumber;
    private String externalNumber;
    private TelephoneConnectionInterface telephoneConnectionInterface;

}
