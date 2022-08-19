package ru.trudexpert.server.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class OrganizationAgentDTO {
    private Long id;

    private String surname;

    private String name;

    private String patronymic;

    private String document;

    private String post;
}
