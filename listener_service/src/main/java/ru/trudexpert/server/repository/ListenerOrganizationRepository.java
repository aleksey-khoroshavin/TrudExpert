package ru.trudexpert.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.trudexpert.server.entity.ListenerOrganization;
import ru.trudexpert.server.entity.OrganizationListenerKey;

public interface ListenerOrganizationRepository extends JpaRepository<ListenerOrganization, OrganizationListenerKey> {

}
