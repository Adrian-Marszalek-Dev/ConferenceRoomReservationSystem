package pl.sdaacademy.ConferenceRoomReservationSystem.organization.args;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class ValidateUpdateOrganizationArgumentProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(
                        """
                                {
                                    "description": "IT company",
                                    "name": "I"
                                }
                                """,
                        false,
                        "size must be between 2 and 20"
                ),
                Arguments.of(
                        """
                                {
                                    "description": "IT company",
                                    "name": ""
                                }
                                """,
                        false,
                        "size must be between 2 and 20"
                ),
                Arguments.of(
                        """
                                {
                                    "description": "IT company"
                                }
                                """,
                        true,
                        "size must be between 2 and 20"
                )
        );
    }
}
