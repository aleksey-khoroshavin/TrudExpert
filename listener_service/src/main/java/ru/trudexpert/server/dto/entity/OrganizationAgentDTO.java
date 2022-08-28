package ru.trudexpert.server.dto.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.trudexpert.server.entity.OrganizationAgent;

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

    public static OrganizationAgentDTO getFromEntity(OrganizationAgent entity){
        if(entity == null){
            return null;
        }

        return new OrganizationAgentDTO()
                .setId(entity.getId())
                .setSurname(entity.getSurname())
                .setName(entity.getName())
                .setPatronymic(entity.getPatronymic())
                .setDocument(entity.getDocument())
                .setPost(entity.getPost());
    }
}
