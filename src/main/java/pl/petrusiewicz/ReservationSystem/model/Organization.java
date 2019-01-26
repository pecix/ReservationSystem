package pl.petrusiewicz.ReservationSystem.model;

import lombok.Data;
import pl.petrusiewicz.ReservationSystem.entity.OrganizationEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class Organization {

    @NotNull
    @Size(min = 2, max = 20)
    @NotBlank
    private String name;

    public OrganizationEntity convertToEntity() {
        var organizationEntity = new OrganizationEntity();
        organizationEntity.setName(name);
        return organizationEntity;
    }
}


