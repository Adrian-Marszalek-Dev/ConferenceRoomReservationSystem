package pl.sdaacademy.ConferenceRoomReservationSystem.organization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.sdaacademy.ConferenceRoomReservationSystem.SortType;

import java.util.List;
import java.util.NoSuchElementException;

@Service
class OrganizationService {

    private final OrganizationRepository organizationRepository;

    @Autowired
    OrganizationService(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    List<Organization> getAllOrganizations(SortType sortType) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortType.name()), "name");
        return organizationRepository.findAll(sort);
    }

    Organization getOrganizations(String name) {
        return organizationRepository.findById(name).orElseThrow(() -> new NoSuchElementException("No organization exists!"));
    }

    Organization addOrganization(Organization organization) {
        organizationRepository.findById(organization.getName()).ifPresent(o -> {
            throw new IllegalArgumentException("Organization already exists!");
        });
        return organizationRepository.save(organization);
    }

    Organization deleteOrganization(String name) {
        Organization organization = organizationRepository.findById(name).orElseThrow(() -> new NoSuchElementException(""));
        organizationRepository.deleteById(name);
        return organization;
    }

    Organization updateOrganization(String name, Organization organization) {
        Organization organizationToUpdate = organizationRepository
                .findById(organization.getName())
                .orElseThrow(() -> new NoSuchElementException(""));
        if (organization.getDescription() != null) {
            organizationToUpdate.setDescription(organization.getDescription());
        }
        if (organization.getName() != null && !organization.getName().equals(organizationToUpdate.getName())) {
            organizationToUpdate.setName(organization.getName());
        }
        return organizationRepository.save(organizationToUpdate);
    }
}
