package ru.trudexpert.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.trudexpert.server.entity.OrganizationAgent;

@Repository
public interface OrganizationAgentRepository extends JpaRepository<OrganizationAgent, Long> {
}
