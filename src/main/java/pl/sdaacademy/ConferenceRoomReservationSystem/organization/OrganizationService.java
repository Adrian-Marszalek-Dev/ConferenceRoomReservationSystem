package pl.sdaacademy.ConferenceRoomReservationSystem.organization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.sdaacademy.ConferenceRoomReservationSystem.SortType;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
class OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final OrganizationTransformer organizationTransformer;

    @Autowired
    OrganizationService(OrganizationRepository organizationRepository, OrganizationTransformer organizationTransformer) {
        this.organizationRepository = organizationRepository;
        this.organizationTransformer = organizationTransformer;
    }

    List<OrganizationDto> getAllOrganizations(SortType sortType) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortType.name()), "name");
        return organizationRepository.findAll(sort).stream()
                .map(organizationTransformer::toDto)
                .collect(Collectors.toList());
    }

    OrganizationDto getOrganization(String name) {
        return organizationRepository.findByName(name)
                .map(organizationTransformer::toDto)
                .orElseThrow(() -> new NoSuchElementException("No organization exists!"));
    }

    OrganizationDto addOrganization(OrganizationDto organizationDto) {
        Organization organization = organizationTransformer.fromDto(organizationDto);
        organizationRepository.findByName(organization.getName()).ifPresent(o -> {
            throw new IllegalArgumentException("Organization already exists!");
        });
        return organizationTransformer.toDto(organizationRepository.save(organization));
    }

    OrganizationDto deleteOrganization(String name) {
        Organization organization = organizationRepository.findByName(name).orElseThrow(() -> new NoSuchElementException(""));
        organizationRepository.deleteById(organization.getId());
        return organizationTransformer.toDto(organization);
    }

    OrganizationDto updateOrganization(String name, OrganizationDto organizationDto) {
        Organization organization = organizationTransformer.fromDto(organizationDto);
        Organization organizationToUpdate = organizationRepository
                .findByName(name)
                .orElseThrow(() -> new NoSuchElementException(""));
        if (organization.getDescription() != null) {
            organizationToUpdate.setDescription(organization.getDescription());
        }
        if (organization.getName() != null && !organization.getName().equals(organizationToUpdate.getName())) {
            organizationRepository.findByName(organization.getName())
                            .ifPresent(o -> {
                                throw new IllegalArgumentException("Organization already exists!");
                            });
            organizationToUpdate.setName(organization.getName());
        }
        return organizationTransformer.toDto(organizationRepository.save(organizationToUpdate));
    }
}
