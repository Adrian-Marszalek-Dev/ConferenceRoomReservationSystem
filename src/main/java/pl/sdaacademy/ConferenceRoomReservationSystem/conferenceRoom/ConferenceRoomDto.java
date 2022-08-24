package pl.sdaacademy.ConferenceRoomReservationSystem.conferenceRoom;

import javax.validation.constraints.*;
import java.util.Objects;

interface AddConferenceRoom{
}

interface UpdateConferenceRoom{
}
public class ConferenceRoomDto {

    private String id;
    @NotBlank(groups = AddConferenceRoom.class)
    @Size(min = 2, max = 20, groups = {AddConferenceRoom.class, UpdateConferenceRoom.class})
    private String name;

    @Size(min = 2, max = 20, groups = {AddConferenceRoom.class, UpdateConferenceRoom.class})
    private String identifier;

    @Min(value = 0, groups = {AddConferenceRoom.class, UpdateConferenceRoom.class})
    @Max(value = 10, groups = {AddConferenceRoom.class, UpdateConferenceRoom.class})
    private Integer level;
    private Boolean isAvailable;

    @NotNull(groups = AddConferenceRoom.class)
    @PositiveOrZero(groups = {AddConferenceRoom.class, UpdateConferenceRoom.class})
    private Integer numberOfSeats;
    @NotNull
    private String organization;

    public ConferenceRoomDto() {
    }

    public ConferenceRoomDto(String id, String name, String identifier, Integer level, Boolean isAvailable, Integer numberOfSeats, String organization) {
        this.id = id;
        this.name = name;
        this.identifier = identifier;
        this.level = level;
        this.isAvailable = isAvailable;
        this.numberOfSeats = numberOfSeats;
        this.organization = organization;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConferenceRoomDto that = (ConferenceRoomDto) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(identifier, that.identifier) && Objects.equals(level, that.level) && Objects.equals(isAvailable, that.isAvailable) && Objects.equals(numberOfSeats, that.numberOfSeats) && Objects.equals(organization, that.organization);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, identifier, level, isAvailable, numberOfSeats, organization);
    }
}
