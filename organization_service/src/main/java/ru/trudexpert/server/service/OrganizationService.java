package ru.trudexpert.server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.trudexpert.server.dto.OrganizationDTO;
import ru.trudexpert.server.entity.Organization;
import ru.trudexpert.server.exception.NoOrganizationNameException;
import ru.trudexpert.server.exception.OrganizationAlreadyRegisteredException;
import ru.trudexpert.server.repository.OrganizationRepository;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    private void checkCompanyName(String name) throws OrganizationAlreadyRegisteredException, NoOrganizationNameException {
        if(name.isEmpty() || name == null){
            throw new NoOrganizationNameException();
        }

        if(organizationRepository.existsByName(name)){
            throw new OrganizationAlreadyRegisteredException();
        }
    }

    @Transactional
    public void createOrganization(OrganizationDTO dto) throws OrganizationAlreadyRegisteredException, NoOrganizationNameException {
        checkCompanyName(dto.getOrganizationName());

        Organization organization = Organization.getFromDTO(dto);
        organizationRepository.save(organization);
    }

}
