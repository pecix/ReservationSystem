package pl.petrusiewicz.ReservationSystem.model;

import lombok.Data;
import pl.petrusiewicz.ReservationSystem.entity.TelephoneEntity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class Telephone {

    @Min(value = 1, message = "min value 1")
    @Max(value = 100, message = "max value 100")
    private int internalNumber;
    private String externalNumber;
    private TelephoneConnectionInterface telephoneConnectionInterface;

    public TelephoneEntity convertToEntity() {
        var telephoneEntity = new TelephoneEntity();
        telephoneEntity.setInternalNumber(internalNumber);
        telephoneEntity.setExternalNumber(externalNumber);
        telephoneEntity.setTelephoneConnectionInterface(telephoneConnectionInterface);
        return telephoneEntity;
    }
}
