package ru.trudexpert.server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.trudexpert.server.dto.OrganizationAgentDTO;
import ru.trudexpert.server.dto.OrganizationFullDTO;
import ru.trudexpert.server.dto.OrganizationShortInfoDTO;
import ru.trudexpert.server.entity.Organization;
import ru.trudexpert.server.entity.OrganizationAgent;
import ru.trudexpert.server.exception.NoOrganizationNameException;
import ru.trudexpert.server.exception.OrganizationAlreadyRegisteredException;
import ru.trudexpert.server.exception.OrganizationNotExistException;
import ru.trudexpert.server.repository.OrganizationAgentRepository;
import ru.trudexpert.server.repository.OrganizationRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final OrganizationAgentRepository agentRepository;

    private void checkCompanyName(String name) throws OrganizationAlreadyRegisteredException, NoOrganizationNameException {
        if(name.isEmpty()){
            throw new NoOrganizationNameException();
        }

        if(organizationRepository.existsByName(name)){
            throw new OrganizationAlreadyRegisteredException();
        }
    }

    @Transactional
    public void saveOrganization(OrganizationFullDTO dto) throws OrganizationAlreadyRegisteredException, NoOrganizationNameException {
        checkCompanyName(dto.getOrganizationDTO().getOrganizationName());

        Organization organization = Organization.getFromDTO(dto.getOrganizationDTO());

        if(existAgentFullNameAnyField(dto.getOrganizationAgentDTO())){
            OrganizationAgent agent = OrganizationAgent.getFromDTO(dto.getOrganizationAgentDTO());
            attachAgentToOrganization(agent, organization);
        }

        organizationRepository.save(organization);
    }

    @Transactional
    public void saveOrganizationAgent(OrganizationAgentDTO dto, String organizationName) throws OrganizationNotExistException {
        Organization organization = organizationRepository.findByName(organizationName).orElse(null);
        if(organization == null){
            throw new OrganizationNotExistException();
        }
        OrganizationAgent agent = OrganizationAgent.getFromDTO(dto);
        attachAgentToOrganization(agent, organization);
    }

    @Transactional
    public List<OrganizationShortInfoDTO> getOrganizationsByName(String companyName){
        return organizationRepository.findAllByName(companyName)
                .stream()
                .map(OrganizationShortInfoDTO::getFromEntity)
                .toList();
    }

    @Transactional
    public List<OrganizationShortInfoDTO> getAllOrganizations(){
        return organizationRepository.findAll()
                .stream()
                .map(OrganizationShortInfoDTO::getFromEntity)
                .toList();
    }

    @Transactional
    public OrganizationAgentDTO getAgentById(Long id){
        OrganizationAgent agent = agentRepository.findById(id).orElse(null);

        return OrganizationAgentDTO.getFromEntity(agent);
    }

    private void attachAgentToOrganization(OrganizationAgent agent, Organization organization){
        agent.setId(organization.getId());
        agent.setOrganization(organization);
        organization.setOrganizationAgent(agent);
    }

    private boolean existAgentFullNameAnyField(OrganizationAgentDTO dto){
        return !dto.getSurname().isEmpty() || !dto.getName().isEmpty();
    }

}
