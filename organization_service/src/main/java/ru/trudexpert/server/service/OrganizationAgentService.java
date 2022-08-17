package ru.trudexpert.server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.trudexpert.server.repository.OrganizationAgentRepository;

@Service
@RequiredArgsConstructor
public class OrganizationAgentService {

    private final OrganizationAgentRepository organizationAgentRepository;


}
