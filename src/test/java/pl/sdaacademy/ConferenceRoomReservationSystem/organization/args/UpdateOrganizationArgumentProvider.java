package pl.sdaacademy.ConferenceRoomReservationSystem.organization.args;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import pl.sdaacademy.ConferenceRoomReservationSystem.organization.Organization;
import pl.sdaacademy.ConferenceRoomReservationSystem.organization.OrganizationDto;

import java.util.stream.Stream;

public class UpdateOrganizationArgumentProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                //name
                //existing org
                //to update
                //expected
                Arguments.of(
                        "Intive",
                        new Organization("Intive", "Delivery company"),
                        new OrganizationDto(null, "IT company"),
                        new Organization(null, "IT company"),
                        new Organization("Intive", "IT company"),
                        new OrganizationDto("Intive", "IT company")

                ),
                Arguments.of(
                        "Intive",
                        new Organization("Intive", "Delivery company"),
                        new OrganizationDto("Tieto", null),
                        new Organization("Tieto", null),
                        new Organization("Tieto", "Delivery company"),
                        new OrganizationDto("Tieto", "Delivery company")
                ),
                Arguments.of(
                        "Intive",
                        new Organization("Intive", "Delivery company"),
                        new OrganizationDto("Tieto", "IT company"),
                        new Organization("Tieto", "IT company"),
                        new Organization("Tieto", "IT company"),
                        new OrganizationDto("Tieto", "IT company")
                ),
                Arguments.of(
                        "Intive",
                        new Organization("Intive", "Delivery company"),
                        new OrganizationDto(null, null),
                        new Organization(null, null),
                        new Organization("Intive", "Delivery company"),
                        new OrganizationDto("Intive", "Delivery company")
                )
        );
    }
}
