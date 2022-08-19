package pl.sdaacademy.ConferenceRoomReservationSystem.organization;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Organization {

    @Id
    @Size(min = 2, max = 20, groups = {AddOrganization.class, UpdateOrganization.class})
    @NotBlank(groups = AddOrganization.class)
    private String name;
    private String description;

    public Organization() {

    }

    public Organization(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

interface AddOrganization { }
interface UpdateOrganization { }
