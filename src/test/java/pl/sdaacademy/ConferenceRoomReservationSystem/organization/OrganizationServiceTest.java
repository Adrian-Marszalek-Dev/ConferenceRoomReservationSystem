package pl.sdaacademy.ConferenceRoomReservationSystem.organization;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.sdaacademy.ConferenceRoomReservationSystem.SortType;
import pl.sdaacademy.ConferenceRoomReservationSystem.organization.args.SortOrganizationArgumentProvider;
import pl.sdaacademy.ConferenceRoomReservationSystem.organization.args.UpdateOrganizationArgumentProvider;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
class OrganizationServiceTest {

    @MockBean
    OrganizationRepository organizationRepository;
    @Autowired
    OrganizationService organizationService;

    @ParameterizedTest
    @ArgumentsSource(SortOrganizationArgumentProvider.class)
    void when_get_all_with_arg_0_order_then_arg_1_order_info_should_be_provided_to_repo(SortType arg0, Sort arg1) {
        //given
        ArgumentCaptor<Sort> sortArgumentCaptor = ArgumentCaptor.forClass(Sort.class);

        //when
        organizationService.getAllOrganizations(arg0);

        //then
        Mockito.verify(organizationRepository).findAll(sortArgumentCaptor.capture());
        assertEquals(arg1, sortArgumentCaptor.getValue());
    }

    @Test
    void when_add_invalid_organization_then_exception_should_be_thrown() {
        //given
        String name = "Intive";
        Organization arg = new Organization(name, "IT company");
        Mockito.when(organizationRepository.findByName(name)).thenReturn(Optional.of(arg));
        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> {
            organizationService.addOrganization(arg);
        });
    }

    @Test
    void when_add_new_organization_then_it_should_be_added_to_repo() {
        //given
        String name = "Intive";
        Organization arg = new Organization(name, "IT company");
        Mockito.when(organizationRepository.findByName(name)).thenReturn(Optional.empty());
        Mockito.when(organizationRepository.save(arg)).thenReturn(arg);

        //when
        Organization result = organizationService.addOrganization(arg);

        //then
        assertEquals(arg, result);
        Mockito.verify(organizationRepository).save(arg);

    }

    @Test
    void when_delete_existing_organization_then_it_should_be_removed_from_the_repo() {
        //given
        String name = "Intive";
        Long id = 1L;
        Organization arg = new Organization(1L, name, "IT company");
        Mockito.when(organizationRepository.findByName(name)).thenReturn(Optional.of(arg));
        //when
        Organization result = organizationService.deleteOrganization(name);
        //then
        assertEquals(arg, result);
        Mockito.verify(organizationRepository).deleteById(id);
    }

    @Test
    void when_delete_non_existing_organization_then_exception_should_be_thrown() {
        //given
        String name = "Intive";
        Mockito.when(organizationRepository.findByName(name)).thenReturn(Optional.empty());
        //when
        //then
        assertThrows(NoSuchElementException.class, () -> {
            organizationService.deleteOrganization(name);
        });
    }

    @Test
    void when_update_non_existing_organization_then_exception_should_be_thrown() {
        //given
        String name = "Intive";
        Organization arg = new Organization(name, "IT company");
        Mockito.when(organizationRepository.findByName(name)).thenReturn(Optional.empty());
        //when
        //then
        assertThrows(NoSuchElementException.class, () -> {
            organizationService.updateOrganization(name, arg);
        });
    }

    @ParameterizedTest
    @ArgumentsSource(UpdateOrganizationArgumentProvider.class)
    void when_update_arg_1_organization_with_arg_2_data_then_organization_should_be_updated_to_arg_3(String name, Organization arg1, Organization arg2, Organization arg3) {
        //given
        Mockito.when(organizationRepository.findByName(name)).thenReturn(Optional.of(arg1));
        Mockito.when(organizationRepository.save(arg1)).thenReturn(arg3);
        //when
        Organization result = organizationService.updateOrganization(name, arg2);
        //then
        assertEquals(arg3, result);
        Mockito.verify(organizationRepository).save(arg3);
    }

    @Test
    void when_get_non_existing_organization_then_exception_should_be_thrown() {
        //given
        String name = "Intive";
        Mockito.when(organizationRepository.findByName(name)).thenReturn(Optional.empty());
        //when
        //then
        assertThrows(NoSuchElementException.class, () -> {
            organizationService.getOrganization(name);
        });
    }

    @Test
    void when_get_existing_organization_then_organization_should_be_returned() {
        //given
        String name = "Intive";
        Organization arg = new Organization(name, "IT company");
        Mockito.when(organizationRepository.findByName(name)).thenReturn(Optional.of(arg));
        //when
        Organization result = organizationService.getOrganization(name);
        //then
        assertEquals(arg, result);
        Mockito.verify(organizationRepository).findByName(name);
    }

    @Test
    void when_update_organization_name_which_is_not_unique_then_exception_should_be_thrown() {
        //given
        String name1 = "Intive";
        Organization existingOrg1 = new Organization(name1, "Delivery company");
        String name2 = "Tieto";
        Organization existingOrg2 = new Organization(name2, "Delivery company");
        Organization updateOrganization = new Organization(name2, "Delivery company");
        Mockito.when(organizationRepository.findByName(name1)).thenReturn(Optional.of(existingOrg1));
        Mockito.when(organizationRepository.findByName(name2)).thenReturn(Optional.of(existingOrg2));

        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> {
            organizationService.updateOrganization(name1, updateOrganization);
        });
        Mockito.verify(organizationRepository, Mockito.never()).save(updateOrganization);
    }

    @TestConfiguration
    static class OrganizationServiceTestConfig {

        @Bean
        OrganizationService organizationService(OrganizationRepository organizationRepository) {
            return new OrganizationService(organizationRepository);
        }
    }

}