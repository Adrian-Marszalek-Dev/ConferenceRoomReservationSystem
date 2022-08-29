package pl.sdaacademy.ConferenceRoomReservationSystem.reservation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Objects;

interface AddReservation {
}

interface UpdateReservation {
}

public class ReservationDto {

    private String id;

    @NotNull(groups = AddReservation.class)
    private LocalDateTime startDate;
    @NotNull(groups = AddReservation.class)
    private LocalDateTime endDate;

    @Size(min = 2, max = 20, groups = {AddReservation.class, UpdateReservation.class})
    private String reservationName;

    private String conferenceRoomId;

    public ReservationDto() {
    }

    public ReservationDto(String id, LocalDateTime startDate, LocalDateTime endDate, String reservationName,
                          String conferenceRoomId) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reservationName = reservationName;
        this.conferenceRoomId = conferenceRoomId;
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

    public String getConferenceRoomId() {
        return conferenceRoomId;
    }

    public void setConferenceRoomId(String conferenceRoomId) {
        this.conferenceRoomId = conferenceRoomId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationDto that = (ReservationDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(reservationName, that.reservationName) &&
                Objects.equals(conferenceRoomId, that.conferenceRoomId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startDate, endDate, reservationName, conferenceRoomId);
    }

    @Override
    public String toString() {
        return "ReservationDto{" +
                "id='" + id + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", reservationName='" + reservationName + '\'' +
                ", conferenceRoomId='" + conferenceRoomId + '\'' +
                '}';
    }
}
