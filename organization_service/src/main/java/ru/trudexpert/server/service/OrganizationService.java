package ru.trudexpert.server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.trudexpert.server.repository.OrganizationRepository;

@Service
@RequiredArgsConstructor
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

}
