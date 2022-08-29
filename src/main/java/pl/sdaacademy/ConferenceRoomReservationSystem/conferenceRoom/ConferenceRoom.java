package pl.sdaacademy.ConferenceRoomReservationSystem.conferenceRoom;

import org.hibernate.annotations.GenericGenerator;
import pl.sdaacademy.ConferenceRoomReservationSystem.organization.Organization;
import pl.sdaacademy.ConferenceRoomReservationSystem.reservation.Reservation;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;



@Entity
public class ConferenceRoom {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )

    private String id;
    private String name;
    private String identifier;
    private Integer level;
    private Boolean isAvailable;
    private Integer numberOfSeats;

    @ManyToOne
    private Organization organization;

    @OneToMany(mappedBy = "conferenceRoom")
    private List<Reservation> reservations;

    public ConferenceRoom(){
    }

    public ConferenceRoom(String id) {
        this.id = id;
    }

    public ConferenceRoom(String id, String name, String identifier, Integer level, Boolean isAvailable, Integer numberOfSeats, Organization organization, List<Reservation> reservations) {
        this.id = id;
        this.name = name;
        this.identifier = identifier;
        this.level = level;
        this.isAvailable = isAvailable;
        this.numberOfSeats = numberOfSeats;
        this.organization = organization;
        this.reservations = reservations;
    }

    public ConferenceRoom(String name, String identifier, Integer level, Boolean isAvailable, Integer numberOfSeats, Organization organization) {
        this.name = name;
        this.identifier = identifier;
        this.level = level;
        this.isAvailable = isAvailable;
        this.numberOfSeats = numberOfSeats;
        this.organization = organization;
    }

    public ConferenceRoom(String id, String name, String identifier, Integer level, Boolean isAvailable, Integer numberOfSeats) {
        this.id = id;
        this.name = name;
        this.identifier = identifier;
        this.level = level;
        this.isAvailable = isAvailable;
        this.numberOfSeats = numberOfSeats;
    }

    public ConferenceRoom(String id, String name, String identifier, Integer level, Boolean isAvailable, Integer numberOfSeats, Organization organization) {
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

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    @Override
    public String toString() {
        return "ConferenceRoom{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", identifier='" + identifier + '\'' +
                ", level=" + level +
                ", isAvailable=" + isAvailable +
                ", numberOfSeats=" + numberOfSeats +
                ", organization=" + organization +
                ", reservations=" + reservations +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConferenceRoom that = (ConferenceRoom) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(identifier, that.identifier) && Objects.equals(level, that.level) && Objects.equals(isAvailable, that.isAvailable) && Objects.equals(numberOfSeats, that.numberOfSeats) && Objects.equals(organization, that.organization) && Objects.equals(reservations, that.reservations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, identifier, level, isAvailable, numberOfSeats, organization, reservations);
    }
}
