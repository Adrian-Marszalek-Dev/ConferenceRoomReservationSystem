package pl.sdaacademy.ConferenceRoomReservationSystem.organization.args;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import pl.sdaacademy.ConferenceRoomReservationSystem.SortType;

import java.util.stream.Stream;

public class SortTypeArgumentProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(
                        "?sortType=ASC",
                        SortType.ASC
                ),
                Arguments.of(
                        "?sortType=DESC",
                        SortType.DESC
                ),
                Arguments.of(
                        "",
                        SortType.ASC
                )
        );
    }
}
