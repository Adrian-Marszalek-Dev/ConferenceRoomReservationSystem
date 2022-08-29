package pl.sdaacademy.ConferenceRoomReservationSystem.conferenceRoom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import pl.sdaacademy.ConferenceRoomReservationSystem.organization.Organization;
import pl.sdaacademy.ConferenceRoomReservationSystem.organization.OrganizationRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
class ConferenceRoomService {

    private final ConferenceRoomRepository conferenceRoomRepository;
    private final OrganizationRepository organizationRepository;
    private final ConferenceRoomUpdater conferenceRoomUpdater;
    private final ConferenceRoomTransformer conferenceRoomTransformer;

    @Autowired
    ConferenceRoomService(ConferenceRoomRepository conferenceRoomRepository,
                          OrganizationRepository organizationRepository,
                          ConferenceRoomUpdater conferenceRoomUpdater,
                          ConferenceRoomTransformer conferenceRoomTransformer) {
        this.conferenceRoomRepository = conferenceRoomRepository;
        this.organizationRepository = organizationRepository;
        this.conferenceRoomUpdater = conferenceRoomUpdater;
        this.conferenceRoomTransformer = conferenceRoomTransformer;
    }

    List<ConferenceRoomDto> getAllConferenceRooms() {
        return conferenceRoomRepository.findAll().stream()
                .map(conferenceRoomTransformer::toDto)
                .collect(Collectors.toList());
    }

    ConferenceRoomDto getConferenceRoomById(String id) {
        return conferenceRoomRepository.findById(id)
                .map(conferenceRoomTransformer::toDto)
                .orElseThrow(() -> {
                    throw new NoSuchElementException("No conference room found!");
                });
    }

    List<ConferenceRoomDto> getConferenceRoomBy(
            String identifier,
            Integer level,
            String organizationName,
            Boolean availability,
            Integer numberOfSeats
    ) {
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnoreNullValues();
        Example<ConferenceRoom> conferenceRoomExample = Example.of(
                new ConferenceRoom(null, identifier, level, availability, numberOfSeats,
                        new Organization(organizationName)),
                exampleMatcher
        );
        return conferenceRoomRepository.findAll(conferenceRoomExample).stream()
                .map(conferenceRoomTransformer::toDto)
                .collect(Collectors.toList());
    }

    ConferenceRoomDto addConferenceRoom(ConferenceRoomDto conferenceRoomDto) {
        ConferenceRoom conferenceRoom = conferenceRoomTransformer.fromDto(conferenceRoomDto);
        Organization organizationFromRepo = organizationRepository.findByName(conferenceRoom.getOrganization().getName())
                .orElseThrow(() -> {
                    throw new NoSuchElementException("No organization found!");
                });
        conferenceRoom.setOrganization(organizationFromRepo);
        conferenceRoomRepository.findByNameAndOrganization_Name(conferenceRoom.getName(),
                        conferenceRoom.getOrganization().getName())
                .ifPresent(cr -> {
                    throw new IllegalArgumentException("Conference room already exists!");
                });
        return conferenceRoomTransformer.toDto(conferenceRoomRepository.save(conferenceRoom));
    }

    ConferenceRoomDto deleteConferenceRoom(String id) {
        ConferenceRoom conferenceRoom = conferenceRoomRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No conference room found!"));
        conferenceRoomRepository.deleteById(id);
        return conferenceRoomTransformer.toDto(conferenceRoom);
    }

    ConferenceRoomDto updateConferenceRoom(String id, ConferenceRoomDto conferenceRoom) {
        return conferenceRoomTransformer.toDto(
                conferenceRoomUpdater.update(id, conferenceRoomTransformer.fromDto(conferenceRoom))
        );
    }

}
