package pl.sdaacademy.ConferenceRoomReservationSystem.organization;

import pl.sdaacademy.ConferenceRoomReservationSystem.conferenceRoom.ConferenceRoom;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
public class Organization {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "organization")
    private List<ConferenceRoom> conferenceRooms = new ArrayList<>();

    public Organization() {
    }

    public Organization(String name) {
        this.name = name;
    }

    public Organization(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Organization(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Organization(Long id, String name, String description, List<ConferenceRoom> conferenceRooms) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.conferenceRooms = conferenceRooms;
    }

    public List<ConferenceRoom> getConferenceRooms() {
        return conferenceRooms;
    }

    public void setConferenceRooms(List<ConferenceRoom> conferenceRooms) {
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

    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", conferenceRooms=" + conferenceRooms +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }
}
