package pl.sdaacademy.ConferenceRoomReservationSystem.organization;

import pl.sdaacademy.ConferenceRoomReservationSystem.conferenceRoom.ConferenceRoomDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

interface AddOrganization {
}

interface UpdateOrganization {
}

public class OrganizationDto {

    private Long id;
    @NotBlank(groups = AddOrganization.class)
    @Size(min = 2, max = 20, groups = {AddOrganization.class, UpdateOrganization.class})
    private String name;
    private String description;
    private List<ConferenceRoomDto> conferenceRooms = new ArrayList<>();

    public OrganizationDto(){
    }

    public OrganizationDto(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public OrganizationDto(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public OrganizationDto(Long id, String name, String description, List<ConferenceRoomDto> conferenceRooms) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.conferenceRooms = conferenceRooms;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<ConferenceRoomDto> getConferenceRooms() {
        return conferenceRooms;
    }

    public void setConferenceRooms(List<ConferenceRoomDto> conferenceRooms) {
        this.conferenceRooms = conferenceRooms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganizationDto that = (OrganizationDto) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(conferenceRooms, that.conferenceRooms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, conferenceRooms);
    }
}
