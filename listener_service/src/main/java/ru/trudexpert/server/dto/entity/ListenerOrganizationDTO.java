package ru.trudexpert.server.dto.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class ListenerOrganizationDTO {
    private Long organizationId;
    private String organizationName;
    private String post;
}
