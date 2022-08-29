package pl.sdaacademy.ConferenceRoomReservationSystem.reservation;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservations")
class ReservationController {

    private final ReservationService reservationService;

    ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    ReservationDto add(@RequestBody ReservationDto reservationDto){
        return reservationService.addReservation(reservationDto);
    }

    @PutMapping("/{id}")
    ReservationDto update(@PathVariable String id, @RequestBody ReservationDto reservationDto){
        return reservationService.updateReservation(id, reservationDto);
    }
}
