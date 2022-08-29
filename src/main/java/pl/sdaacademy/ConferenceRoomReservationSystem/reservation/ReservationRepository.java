package pl.sdaacademy.ConferenceRoomReservationSystem.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, String> {
    Optional<Reservation> findByConferenceRoom_IdAndStartDateLessThanAndEndDateGreaterThan(
            String conferenceRoomId,
            LocalDateTime endDate,
            LocalDateTime startDate
    );
}
