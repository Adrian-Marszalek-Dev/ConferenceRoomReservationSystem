package pl.sdaacademy.ConferenceRoomReservationSystem.organization.args;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import pl.sdaacademy.ConferenceRoomReservationSystem.organization.Organization;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

public class GetAllOrganizationArgumentProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(
                        Arrays.asList(
                                new Organization("test", "IT Company"),
                                new Organization("Google", "IT company"),
                                new Organization("Uber", "Delivery company")
                        ),
                        Arrays.asList(
                                new Organization("test", "IT Company"),
                                new Organization("Google", "IT company"),
                                new Organization("Uber", "Delivery company")
                        )
                ),
                Arguments.of(Collections.emptyList(), Collections.emptyList())
        );
    }
}
