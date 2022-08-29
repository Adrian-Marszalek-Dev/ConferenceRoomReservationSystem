package pl.sdaacademy.ConferenceRoomReservationSystem.reservation.args;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.time.LocalDateTime;
import java.util.stream.Stream;

public class FindReservationByDateArgumentProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(
                        LocalDateTime.of(2022, 8, 20, 10, 30, 0),
                        LocalDateTime.of(2022, 8, 20, 11, 0, 0),
                        true
                ),
                Arguments.of(
                        LocalDateTime.of(2022, 8, 20, 9, 30, 0),
                        LocalDateTime.of(2022, 8, 20, 12, 0, 0),
                        true
                ),
                Arguments.of(
                        LocalDateTime.of(2022, 8, 20, 9, 30, 0),
                        LocalDateTime.of(2022, 8, 20, 10, 30, 0),
                        true
                ),
                Arguments.of(
                        LocalDateTime.of(2022, 8, 20, 10, 30, 0),
                        LocalDateTime.of(2022, 8, 20, 12, 0, 0),
                        true
                ),
                Arguments.of(
                        LocalDateTime.of(2022, 8, 20, 9, 30, 0),
                        LocalDateTime.of(2022, 8, 20, 10, 0, 0),
                        false
                ),
                Arguments.of(
                        LocalDateTime.of(2022, 8, 20, 11, 0, 0),
                        LocalDateTime.of(2022, 8, 20, 12, 0, 0),
                        false
                ),
                Arguments.of(
                        LocalDateTime.of(2022, 8, 20, 10, 0, 0),
                        LocalDateTime.of(2022, 8, 20, 11, 0, 0),
                        true
                )
        );
    }
}
