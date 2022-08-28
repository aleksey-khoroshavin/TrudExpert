package ru.trudexpert.server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.trudexpert.server.dto.entity.ListenerOrganizationDTO;
import ru.trudexpert.server.entity.Listener;
import ru.trudexpert.server.entity.Organization;
import ru.trudexpert.server.exception.ListenerNotFoundException;
import ru.trudexpert.server.exception.OrganizationNotExistException;
import ru.trudexpert.server.repository.ListenerRepository;
import ru.trudexpert.server.repository.OrganizationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListenerOrganizationService {
    private final ListenerRepository listenerRepository;

    private final OrganizationRepository organizationRepository;

    public List<ListenerOrganizationDTO> getOrganizationsOfListener(Long listenerId){
        return organizationRepository.getListenerOrganizations(listenerId);
    }

    public void addToOrganization(Long organizationId, Long listenerId, String post)
            throws ListenerNotFoundException, OrganizationNotExistException {

        Organization organization = organizationRepository.findById(organizationId).orElse(null);

        if(organization == null){
            throw new OrganizationNotExistException();
        }

        Listener listener = listenerRepository.findById(listenerId).orElse(null);

        if(listener == null){
            throw new ListenerNotFoundException();
        }

        organization.addListener(listener, post);
    }

    public void deleteFromOrganization(Long organizationId, Long listenerId)
            throws ListenerNotFoundException, OrganizationNotExistException {
        Listener listener = listenerRepository.findById(listenerId).orElse(null);

        if(listener == null){
            throw new ListenerNotFoundException();
        }

        Organization organization = organizationRepository.findById(organizationId).orElse(null);

        if(organization == null){
            throw new OrganizationNotExistException();
        }

        organization.removeListener(listener);
    }

}
