package pl.sdaacademy.ConferenceRoomReservationSystem.conferenceRoom;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conferencerooms")
class ConferenceRoomController {

    private final ConferenceRoomService conferenceRoomService;

    ConferenceRoomController(ConferenceRoomService conferenceRoomService) {
        this.conferenceRoomService = conferenceRoomService;
    }

    @GetMapping
    List<ConferenceRoomDto> getAll(
            @RequestParam(required = false) String identifier,
            @RequestParam(required = false) Integer level,
            @RequestParam(required = false) String organizationName,
            @RequestParam(required = false) Boolean availability,
            @RequestParam(required = false) Integer numberOfSeats
    ) {
        return conferenceRoomService.getConferenceRoomBy(identifier, level, organizationName, availability, numberOfSeats);
    }

    @GetMapping("/{id}")
    ConferenceRoomDto getById(@PathVariable String id) {
        return conferenceRoomService.getConferenceRoomById(id);
    }

    @PostMapping
    ConferenceRoomDto add(@Validated(AddConferenceRoom.class) @RequestBody ConferenceRoomDto conferenceRoom) {
        return conferenceRoomService.addConferenceRoom(conferenceRoom);
    }

    @DeleteMapping("/{id}")
    ConferenceRoomDto delete(@PathVariable String id) {
        return conferenceRoomService.deleteConferenceRoom(id);
    }

    @PutMapping("/{id}")
    ConferenceRoomDto update(@PathVariable String id, @Validated(UpdateConferenceRoom.class)
    @RequestBody ConferenceRoomDto conferenceRoom) {
        return conferenceRoomService.updateConferenceRoom(id, conferenceRoom);
    }
}
