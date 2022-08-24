package pl.sdaacademy.ConferenceRoomReservationSystem.conferenceRoom;

import org.hibernate.sql.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conferencerooms")
class ConferenceRoomController {

    private final ConferenceRoomService conferenceRoomService;

    ConferenceRoomController(ConferenceRoomService conferenceRoomService){
        this.conferenceRoomService = conferenceRoomService;
    }

    @GetMapping
    List<ConferenceRoomDto> getAll(){
        return conferenceRoomService.getAllConferenceRooms();
    }

    @PostMapping
    ConferenceRoomDto add(@Validated(AddConferenceRoom.class) @RequestBody ConferenceRoomDto conferenceRoom){
        return conferenceRoomService.addConferenceRoom(conferenceRoom);
    }

    @DeleteMapping("/{id}")
    ConferenceRoomDto delete(@PathVariable String id){
        return conferenceRoomService.deleteConferenceRoom(id);
    }

    @PutMapping("/{id}")
    ConferenceRoomDto update(@PathVariable String id, @Validated(UpdateConferenceRoom.class) @RequestBody ConferenceRoomDto conferenceRoom){
        return conferenceRoomService.updateConferenceRoom(id, conferenceRoom);
    }
}
