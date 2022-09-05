package pl.sdaacademy.ConferenceRoomReservationSystem.reservation;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/reservations")
class ReservationController {

    private final ReservationService reservationService;

    ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    List<ReservationDto> getAll(
            @RequestParam(required = false) String id,
            @RequestParam(required = false) LocalDateTime startDate,
            @RequestParam(required = false) LocalDateTime endDate,
            @RequestParam(required = false) String reservationName,
            @RequestParam(required = false) String conferenceRoomId
    ) {
        return reservationService.getReservationsBy(id, startDate, endDate, reservationName, conferenceRoomId);
    }



    @PostMapping
    ReservationDto add(@RequestBody ReservationDto reservationDto) {
        return reservationService.addReservation(reservationDto);
    }

    @PutMapping("/{id}")
    ReservationDto update(@PathVariable String id, @RequestBody ReservationDto reservationDto) {
        return reservationService.updateReservation(id, reservationDto);
    }

    @DeleteMapping("/{id}")
    ReservationDto delete(String id) {
        return reservationService.deleteReservation(id);
    }
}
