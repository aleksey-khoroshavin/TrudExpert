package ru.trudexpert.server.dto.complex;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.trudexpert.server.dto.entity.OrganizationAgentDTO;
import ru.trudexpert.server.dto.entity.OrganizationDTO;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class OrganizationFullDTO {
    private OrganizationDTO organizationDTO;
    private OrganizationAgentDTO organizationAgentDTO;
}
