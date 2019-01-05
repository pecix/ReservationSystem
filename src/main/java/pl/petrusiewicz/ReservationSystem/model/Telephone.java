package pl.petrusiewicz.ReservationSystem.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

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

    //=========================================================


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInternalNumber() {
        return internalNumber;
    }

    public void setInternalNumber(int internalNumber) {
        this.internalNumber = internalNumber;
    }

    public String getExternalNumber() {
        return externalNumber;
    }

    public void setExternalNumber(String externalNumber) {
        this.externalNumber = externalNumber;
    }

    public TelephoneConnectionInterface getTelephoneConnectionInterface() {
        return telephoneConnectionInterface;
    }

    public void setTelephoneConnectionInterface(TelephoneConnectionInterface telephoneConnectionInterface) {
        this.telephoneConnectionInterface = telephoneConnectionInterface;
    }

    //==========================================================


    @Override
    public String toString() {
        return "Telephone{" +
                "id=" + id +
                ", internalNumber=" + internalNumber +
                ", externalNumber='" + externalNumber + '\'' +
                ", telephoneConnectionInterface=" + telephoneConnectionInterface +
                '}';
    }
}
