package pl.sdaacademy.ConferenceRoomReservationSystem.reservation;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import pl.sdaacademy.ConferenceRoomReservationSystem.conferenceRoom.ConferenceRoom;
import pl.sdaacademy.ConferenceRoomReservationSystem.conferenceRoom.ConferenceRoomRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
class ReservationService {

    private static final int MIN_DURATION_OF_THE_MEETING = 15;

    private final ReservationRepository reservationRepository;
    private final ConferenceRoomRepository conferenceRoomRepository;
    private final ReservationTransformer reservationTransformer;


    ReservationService(ReservationRepository reservationRepository,
                       ConferenceRoomRepository conferenceRoomRepository,
                       ReservationTransformer reservationTransformer) {
        this.reservationRepository = reservationRepository;
        this.conferenceRoomRepository = conferenceRoomRepository;
        this.reservationTransformer = reservationTransformer;
    }

    ReservationDto addReservation(ReservationDto reservationDto) {
        Reservation reservation = reservationTransformer.fromDto(reservationDto);
        ConferenceRoom conferenceRoom = conferenceRoomRepository.findById(reservation.getConferenceRoom().getId())
                .orElseThrow(() -> new NoSuchElementException("Can't find conference room!"));
        reservation.setConferenceRoom(conferenceRoom);
        validateConferenceRoom(conferenceRoom);
        validateReservationDuration(reservation);
        validateReservationTime(conferenceRoom, reservation);
        return reservationTransformer.toDto(reservationRepository.save(reservation));
    }

    private void validateReservationTime(ConferenceRoom conferenceRoom, Reservation reservation) {
        reservationRepository.findByConferenceRoom_IdAndStartDateLessThanAndEndDateGreaterThan(
                conferenceRoom.getId(),
                reservation.getEndDate(),
                reservation.getStartDate()
        ).ifPresent(r -> {
            throw new IllegalArgumentException("Reservation during provided time already exist");
        });
    }

    private void validateReservationDuration(Reservation reservation) {
        if (reservation.getEndDate().isBefore(reservation.getStartDate())) {
            throw new IllegalArgumentException("End date is before start date!");
        }
        if (ChronoUnit.MINUTES.between(reservation.getStartDate(),
                reservation.getEndDate()) < MIN_DURATION_OF_THE_MEETING) {
            throw new IllegalArgumentException("Meeting can't be shorter than " +
                    MIN_DURATION_OF_THE_MEETING + " min!");
        }
    }

    private void validateConferenceRoom(ConferenceRoom conferenceRoom) {
        if (!conferenceRoom.getAvailable()) {
            throw new IllegalArgumentException("Conference room is not available");
        }
    }

    ReservationDto updateReservation(String id, ReservationDto reservationDto) {
        Reservation reservation = reservationTransformer.fromDto(reservationDto);
        Reservation reservationFromDb = reservationRepository.getReferenceById(id);
        updateReservationName(reservation, reservationFromDb);
        updateReservationConferenceRoom(reservation, reservationFromDb);
        updateReservationStartAndEndData(reservation, reservationFromDb);
        return reservationTransformer.toDto(reservationRepository.save(reservationFromDb));
    }

    private void updateReservationStartAndEndData(Reservation reservation, Reservation reservationFromDb) {
        LocalDateTime startDate = reservation.getStartDate();
        LocalDateTime endDate = reservation.getEndDate();
        validateReservationDuration(reservation);
        boolean isChange = false;
        if (startDate != null) {
            isChange = true;
            reservationFromDb.setStartDate(startDate);
        }
        if (endDate != null) {
            isChange = true;
            reservationFromDb.setEndDate(endDate);
        }

        //TODO: naprawa błędu aktualizacji nachodzącej
        //aktualna rezerwacja 10-11
        //update 10-10:30
        if (isChange) {
            validateReservationTime(reservationFromDb.getConferenceRoom(), reservationFromDb);
        }
    }

    private void updateReservationName(Reservation reservation, Reservation reservationFromDb) {
        String newReservationName = reservation.getReservationName();
        if (reservation.getReservationName() != null) {
            reservationFromDb.setReservationName(newReservationName);
        }
    }

    private void updateReservationConferenceRoom(Reservation reservation, Reservation reservationFromDb) {
        String conferenceRoomId = reservation.getConferenceRoom().getId();
        if (conferenceRoomId != null) {
            ConferenceRoom conferenceRoom = conferenceRoomRepository.findById(conferenceRoomId)
                    .orElseThrow(() -> {
                        throw new NoSuchElementException("Can't find conference room!");
                    });
            validateConferenceRoom(conferenceRoom);
            reservationFromDb.setConferenceRoom(conferenceRoom);
        }
    }

    List<ReservationDto> getReservationsBy(
            String id,
            LocalDateTime startDate,
            LocalDateTime endDate,
            String reservationName,
            String conferenceRoomId)
    {
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnoreNullValues();
        Example<Reservation> reservationExample = Example.of(
                new Reservation(id,startDate,endDate,reservationName,new ConferenceRoom(conferenceRoomId)),
                exampleMatcher
        );
        return reservationRepository.findAll(reservationExample).stream()
                .map(reservationTransformer::toDto)
                .collect(Collectors.toList());
    }

    ReservationDto deleteReservation(String id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new NoSuchElementException(""));
        reservationRepository.deleteById(reservation.getId());
        return reservationTransformer.toDto(reservation);
    }
}
