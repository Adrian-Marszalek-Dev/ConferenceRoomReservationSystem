package pl.sdaacademy.ConferenceRoomReservationSystem.reservation;

import org.hibernate.annotations.GenericGenerator;
import pl.sdaacademy.ConferenceRoomReservationSystem.conferenceRoom.ConferenceRoom;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )

    private String id;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private String reservationName;

    @ManyToOne
    private ConferenceRoom conferenceRoom;

    public Reservation() {
    }

    public Reservation(LocalDateTime startDate, LocalDateTime endDate, String reservationName,
                       ConferenceRoom conferenceRoom) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.reservationName = reservationName;
        this.conferenceRoom = conferenceRoom;
    }

    public Reservation(String id, LocalDateTime startDate, LocalDateTime endDate, String reservationName,
                       ConferenceRoom conferenceRoom) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reservationName = reservationName;
        this.conferenceRoom = conferenceRoom;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getReservationName() {
        return reservationName;
    }

    public void setReservationName(String reservationName) {
        this.reservationName = reservationName;
    }

    public ConferenceRoom getConferenceRoom() {
        return conferenceRoom;
    }

    public void setConferenceRoom(ConferenceRoom conferenceRoom) {
        this.conferenceRoom = conferenceRoom;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id='" + id + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", reservationName='" + reservationName + '\'' +
                ", conferenceRoom=" + conferenceRoom +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(id, that.id) && Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate) && Objects.equals(reservationName, that.reservationName) &&
                Objects.equals(conferenceRoom, that.conferenceRoom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startDate, endDate, reservationName, conferenceRoom);
    }
}
