package ru.trudexpert.server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.trudexpert.server.dto.entity.OrganizationAgentDTO;
import ru.trudexpert.server.entity.OrganizationAgent;
import ru.trudexpert.server.exception.AgentNotFoundException;
import ru.trudexpert.server.repository.OrganizationAgentRepository;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class OrganizationAgentService {

    private final OrganizationAgentRepository agentRepository;

    @Transactional
    public OrganizationAgentDTO getAgentById(Long id){
        OrganizationAgent agent = agentRepository.findById(id).orElse(null);
        return OrganizationAgentDTO.getFromEntity(agent);
    }

    @Transactional
    public void updateAgent(OrganizationAgentDTO dto) throws AgentNotFoundException {

        OrganizationAgent agent = agentRepository.findById(dto.getId()).orElse(null);

        if(agent == null){
            throw new AgentNotFoundException();
        }

        agent.setId(dto.getId())
                .setSurname(dto.getSurname())
                .setName(dto.getName())
                .setPatronymic(dto.getPatronymic())
                .setDocument(dto.getDocument())
                .setPost(dto.getPost());

        agentRepository.save(agent);

    }



}
