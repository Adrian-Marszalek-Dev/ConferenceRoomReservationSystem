package pl.sdaacademy.ConferenceRoomReservationSystem.organization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
class OrganizationService {

    private final OrganizationRepository organizationRepository;

    @Autowired
    OrganizationService(OrganizationRepository organizationRepository){
        this.organizationRepository = organizationRepository;
    }

    List<Organization> getAllOrganizations(){
        return organizationRepository.findAll();
    }

    Organization addOrganization(Organization organization){
        return organizationRepository.save(organization);
    }

    Organization deleteOrganization(String name){
        Organization organization = organizationRepository.findById(name).orElseThrow(() -> new NoSuchElementException(""));
        organizationRepository.deleteById(name);
        return organization;
    }

    Organization updateOrganization(String name, Organization organization){
        Organization organizationToUpdate = organizationRepository
                .findById(organization.getName())
                .orElseThrow(() -> new NoSuchElementException(""));
        if (organization.getDescription() != null){
            organizationToUpdate.setDescription(organization.getDescription());
        }
        if (organization.getName() != null && !organization.getName().equals(organizationToUpdate.getName())){
            organizationToUpdate.setName(organization.getName());
        }
        return organizationRepository.save(organizationToUpdate);
    }
}
