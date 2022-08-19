package pl.sdaacademy.ConferenceRoomReservationSystem.organization.args;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.springframework.data.domain.Sort;
import pl.sdaacademy.ConferenceRoomReservationSystem.SortType;

import java.util.stream.Stream;

public class SortOrganizationArgumentProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(
                        SortType.ASC,
                        Sort.by(Sort.Direction.ASC, "name")
                ),
                Arguments.of(
                        SortType.DESC,
                        Sort.by(Sort.Direction.DESC, "name")
                )
        );
    }
}
