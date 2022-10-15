package ru.trudexpert.server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.trudexpert.server.dto.entity.OrganizationAgentDTO;
import ru.trudexpert.server.dto.entity.OrganizationDTO;
import ru.trudexpert.server.dto.complex.OrganizationFullDTO;
import ru.trudexpert.server.dto.shortinfo.OrganizationShortInfoDTO;
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
        if (name.isEmpty()) {
            throw new NoOrganizationNameException();
        }

        if (organizationRepository.existsByName(name)) {
            throw new OrganizationAlreadyRegisteredException();
        }
    }

    @Transactional
    public void saveOrganization(OrganizationFullDTO dto) throws OrganizationAlreadyRegisteredException, NoOrganizationNameException {
        checkCompanyName(dto.getOrganizationDTO().getOrganizationName());

        var organization = Organization.getFromDTO(dto.getOrganizationDTO());

        var agent = OrganizationAgent.getFromDTO(dto.getOrganizationAgentDTO());
        attachAgentToOrganization(agent, organization);

        organizationRepository.save(organization);
    }

    @Transactional
    public List<OrganizationShortInfoDTO> getOrganizationsByName(String companyName) {
        return organizationRepository.findAllByName('%' + companyName + '%')
                .stream()
                .map(OrganizationShortInfoDTO::getFromEntity)
                .toList();
    }

    @Transactional
    public List<OrganizationShortInfoDTO> getAllOrganizations() {
        return organizationRepository.findAllOrganizations()
                .stream()
                .map(OrganizationShortInfoDTO::getFromEntity)
                .toList();
    }

    @Transactional
    public List<OrganizationShortInfoDTO> getAllOrganizationsNotAttachedToListener(Long listenerId) {
        return organizationRepository.findAllOrganizationsNotAttachedToListener(listenerId)
                .stream()
                .map(OrganizationShortInfoDTO::getFromEntity)
                .toList();
    }

    @Transactional
    public List<OrganizationShortInfoDTO> getListenerOrganizations(Long listenerId) {
        return organizationRepository.findAllListenerOrganizations(listenerId)
                .stream()
                .map(OrganizationShortInfoDTO::getFromEntity)
                .toList();
    }

    @Transactional
    public OrganizationFullDTO getOrganizationById(Long id) throws OrganizationNotExistException {
        var organization = organizationRepository.findById(id).orElse(null);

        if (organization == null) {
            throw new OrganizationNotExistException();
        }

        var organizationDTO = OrganizationDTO.getFromEntity(organization);
        var agentDTO = OrganizationAgentDTO.getFromEntity(agentRepository.findById(id).orElse(null));

        return new OrganizationFullDTO().setOrganizationDTO(organizationDTO).setOrganizationAgentDTO(agentDTO);
    }

    private void attachAgentToOrganization(OrganizationAgent agent, Organization organization) {
        agent.setId(organization.getId());
        agent.setOrganization(organization);
        organization.setOrganizationAgent(agent);
    }

    @Transactional
    public void updateOrganizationInfo(OrganizationDTO dto) throws OrganizationNotExistException {

        var organization = organizationRepository.findById(dto.getId()).orElse(null);
        if (organization == null) {
            throw new OrganizationNotExistException();
        }

        organization.setId(dto.getId())
                .setName(dto.getOrganizationName())
                .setLawAddress(dto.getLawAddress())
                .setFactAddress(dto.getFactAddress())
                .setPhone(dto.getPhone())
                .setInn(dto.getInn())
                .setKpp(dto.getKpp())
                .setOrgn(dto.getOrgn())
                .setCheckingAccount(dto.getCheckingAccount())
                .setCorrespondentAccount(dto.getCorrespondentAccount())
                .setEmail(dto.getEmail())
                .setBik(dto.getBik())
                .setOkpo(dto.getOkpo())
                .setOkved(dto.getOkved());

        organizationRepository.save(organization);
    }
}
