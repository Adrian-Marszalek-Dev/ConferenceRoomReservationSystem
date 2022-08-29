package pl.sdaacademy.ConferenceRoomReservationSystem;

import org.springframework.stereotype.Component;
import pl.sdaacademy.ConferenceRoomReservationSystem.conferenceRoom.ConferenceRoom;
import pl.sdaacademy.ConferenceRoomReservationSystem.conferenceRoom.ConferenceRoomRepository;
import pl.sdaacademy.ConferenceRoomReservationSystem.organization.Organization;
import pl.sdaacademy.ConferenceRoomReservationSystem.organization.OrganizationRepository;

import javax.annotation.PostConstruct;

@Component
class AppInitializer {

    private final OrganizationRepository organizationRepository;
    private final ConferenceRoomRepository conferenceRoomRepository;

    public AppInitializer(OrganizationRepository organizationRepository, ConferenceRoomRepository conferenceRoomRepository) {
        this.organizationRepository = organizationRepository;
        this.conferenceRoomRepository = conferenceRoomRepository;
    }

    @PostConstruct
    void init() {
        Organization organization1 = new Organization("Organization1", "some desc");
        Organization organization2 = new Organization("Organization2", "some desc");
        Organization organization3 = new Organization("Organization3", "some desc");

        ConferenceRoom conferenceRoom1 = new ConferenceRoom("room1", "IDE1", 1, false, 10, organization1);
        ConferenceRoom conferenceRoom2 = new ConferenceRoom("room2", "IDE2", 2, true, 8, organization1);
        ConferenceRoom conferenceRoom3 = new ConferenceRoom("room3", "IDE3", 3, false, 3, organization1);

        ConferenceRoom conferenceRoom4 = new ConferenceRoom("room3", "IDE4", 3, false, 4, organization2);
        ConferenceRoom conferenceRoom5 = new ConferenceRoom("room2", "IDE5", 2, true, 8, organization2);
        ConferenceRoom conferenceRoom6 = new ConferenceRoom("room1", "IDE6", 2, false, 6, organization2);

        ConferenceRoom conferenceRoom7 = new ConferenceRoom("room3", "IDE7", 1, false, 6, organization3);
        ConferenceRoom conferenceRoom8 = new ConferenceRoom("room2", "IDE8", 2, false, 4, organization3);
        ConferenceRoom conferenceRoom9 = new ConferenceRoom("room1", "IDE9", 5, true, 9, organization3);

        organizationRepository.save(organization1);
        organizationRepository.save(organization2);
        organizationRepository.save(organization3);

        conferenceRoomRepository.save(conferenceRoom1);
        conferenceRoomRepository.save(conferenceRoom2);
        conferenceRoomRepository.save(conferenceRoom3);
        conferenceRoomRepository.save(conferenceRoom4);
        conferenceRoomRepository.save(conferenceRoom5);
        conferenceRoomRepository.save(conferenceRoom6);
        conferenceRoomRepository.save(conferenceRoom7);
        conferenceRoomRepository.save(conferenceRoom8);
        conferenceRoomRepository.save(conferenceRoom9);

    }

}
