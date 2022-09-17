package ru.trudexpert.server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.trudexpert.server.dto.entity.ListenerOrganizationDTO;
import ru.trudexpert.server.entity.Listener;
import ru.trudexpert.server.entity.Organization;
import ru.trudexpert.server.entity.OrganizationListenerKey;
import ru.trudexpert.server.exception.ListenerNotFoundException;
import ru.trudexpert.server.exception.OrganizationNotExistException;
import ru.trudexpert.server.repository.ListenerOrganizationRepository;
import ru.trudexpert.server.repository.ListenerRepository;
import ru.trudexpert.server.repository.OrganizationRepository;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ListenerOrganizationService {

    private final ListenerOrganizationRepository listenerOrganizationRepository;

    private final ListenerRepository listenerRepository;

    private final OrganizationRepository organizationRepository;

    @Transactional
    public List<ListenerOrganizationDTO> getOrganizationsOfListener(Long listenerId){
        Listener listener = listenerRepository.findById(listenerId).orElse(null);
        if(listener == null){
            return Collections.emptyList();
        }

        return listener.getOrganizations().stream().map(ListenerOrganizationDTO::getFromEntity).toList();
    }

    @Transactional
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

        organizationRepository.save(organization);
        listenerRepository.save(listener);
    }

    @Transactional
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

        organizationRepository.save(organization);
        listenerRepository.save(listener);

        listenerOrganizationRepository.deleteById(new OrganizationListenerKey().setListenerId(listenerId).setOrganizationId(organizationId));
    }

}
