package pl.sdaacademy.ConferenceRoomReservationSystem.reservation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.sdaacademy.ConferenceRoomReservationSystem.conferenceRoom.ConferenceRoom;
import pl.sdaacademy.ConferenceRoomReservationSystem.reservation.args.FindReservationByDateArgumentProvider;

import java.time.LocalDateTime;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class ReservationRepositoryTest {

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    TestEntityManager testEntityManager;

    @ParameterizedTest
    @ArgumentsSource(FindReservationByDateArgumentProvider.class)
    void when_check_if_reservation_with_start_date_and_end_date_exists_then_result_should_be_provided(
            LocalDateTime startDate,
            LocalDateTime endDate,
            boolean exists){
        //given
        ConferenceRoom conferenceRoom = new ConferenceRoom(
                "Test",
                "1.1",
                1,
                true,
                10,
                null
        );
        ConferenceRoom conferenceRoomFromDb = testEntityManager.persist(conferenceRoom);
        Reservation reservation = new Reservation(
                LocalDateTime.of(2022,8,20,10,0,0),
                LocalDateTime.of(2022,8,20,11,0,0),
                "test",
                conferenceRoomFromDb
                );
        testEntityManager.persist(reservation);

        //when
        Optional<Reservation> result = reservationRepository.findByConferenceRoom_IdAndStartDateLessThanAndEndDateGreaterThan(
                conferenceRoomFromDb.getId(),
                endDate,
                startDate
        );

        //then
        Assertions.assertEquals(exists, result.isPresent());

    }

}
