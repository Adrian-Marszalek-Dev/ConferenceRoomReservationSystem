package pl.sdaacademy.ConferenceRoomReservationSystem.conferenceRoom;

import org.springframework.stereotype.Component;
import pl.sdaacademy.ConferenceRoomReservationSystem.organization.Organization;

@Component
public class ConferenceRoomTransformer {

    public ConferenceRoomDto toDto(ConferenceRoom conferenceRoom){
        return new ConferenceRoomDto(
                conferenceRoom.getId(),
                conferenceRoom.getName(),
                conferenceRoom.getIdentifier(),
                conferenceRoom.getLevel(),
                conferenceRoom.getAvailable(),
                conferenceRoom.getNumberOfSeats(),
                conferenceRoom.getOrganization().getName()
        );
    }

    public ConferenceRoom fromDto(ConferenceRoomDto conferenceRoomDto){
        return new ConferenceRoom(
                conferenceRoomDto.getId(),
                conferenceRoomDto.getName(),
                conferenceRoomDto.getIdentifier(),
                conferenceRoomDto.getLevel(),
                conferenceRoomDto.getAvailable(),
                conferenceRoomDto.getNumberOfSeats(),
                new Organization(conferenceRoomDto.getOrganization())
        );
    }
}
