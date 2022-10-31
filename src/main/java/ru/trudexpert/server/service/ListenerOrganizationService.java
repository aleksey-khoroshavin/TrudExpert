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
import java.util.List;

@Service
@RequiredArgsConstructor
public class ListenerOrganizationService {

    private final ListenerOrganizationRepository listenerOrganizationRepository;

    private final ListenerRepository listenerRepository;

    private final OrganizationRepository organizationRepository;

    @Transactional
    public List<ListenerOrganizationDTO> getOrganizationsOfListener(Long listenerId) throws ListenerNotFoundException {
        var listener = listenerRepository.findById(listenerId).orElse(null);
        if (listener == null) {
            throw new ListenerNotFoundException();
        }

        return listener.getOrganizations().stream().map(ListenerOrganizationDTO::getFromEntity).toList();
    }

    @Transactional
    public void addToOrganization(Long organizationId, Long listenerId, String post)
            throws ListenerNotFoundException, OrganizationNotExistException {
        var organization = checkOrganization(organizationId);
        var listener = checkListener(listenerId);

        organization.addListener(listener, post);
        organizationRepository.save(organization);
        listenerRepository.save(listener);
    }

    @Transactional
    public void deleteFromOrganization(Long organizationId, Long listenerId)
            throws ListenerNotFoundException, OrganizationNotExistException {
        var organization = checkOrganization(organizationId);
        var listener = checkListener(listenerId);

        organization.removeListener(listener);
        organizationRepository.save(organization);
        listenerRepository.save(listener);
        listenerOrganizationRepository.deleteById(new OrganizationListenerKey().setListenerId(listenerId).setOrganizationId(organizationId));
    }

    private Organization checkOrganization(Long id) throws OrganizationNotExistException {
        var organization = organizationRepository.findById(id).orElse(null);
        if (organization == null) {
            throw new OrganizationNotExistException();
        }
        return organization;
    }

    private Listener checkListener(Long id) throws ListenerNotFoundException {
        var listener = listenerRepository.findById(id).orElse(null);
        if (listener == null) {
            throw new ListenerNotFoundException();
        }
        return listener;
    }
}
