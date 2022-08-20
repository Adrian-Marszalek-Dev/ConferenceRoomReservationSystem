package pl.sdaacademy.ConferenceRoomReservationSystem.organization;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.sdaacademy.ConferenceRoomReservationSystem.organization.args.GetAllOrganizationArgumentProvider;
import pl.sdaacademy.ConferenceRoomReservationSystem.organization.args.GetByIdOrganizationArgumentProvider;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@DataJpaTest
class OrganizationRepositoryTest {

    @Autowired
    OrganizationRepository organizationRepository;

    @Autowired
    TestEntityManager testEntityManager;

    @ParameterizedTest
    @ArgumentsSource(GetAllOrganizationArgumentProvider.class)
    void when_arg_0_data_are_available_in_repo_then_find_all_should_return_arg_1_list(List<Organization> arg0, List<Organization> arg1) {

        //given
        arg0.forEach(o -> {
            testEntityManager.persist(o);
        });
        //when
        List<Organization> results = organizationRepository.findAll();
        //then
        assertEquals(arg1, results);
    }

    @ParameterizedTest
    @ArgumentsSource(GetByIdOrganizationArgumentProvider.class)
    void when_find_by_arg_1_when_arg_0_list_is_available_then_arg_2_item_should_be_returned(List<Organization> arg0, Long arg1, Optional<Organization> arg2) {
        //given
        arg0.forEach(o -> testEntityManager.persist(o));
        //when
        Optional<Organization> result = organizationRepository.findById(arg1);
        //then
        assertEquals(arg2, result);
    }

    @Test
    void when_save_arg_0_to_repo_then_it_should_be_stored_properly() {
        //given
        Organization arg0 = new Organization(1L, "Intive", "IT Company");
        //when
        organizationRepository.save(arg0);
        //then
        assertEquals(arg0, testEntityManager.find(Organization.class, 1L));
    }


}
