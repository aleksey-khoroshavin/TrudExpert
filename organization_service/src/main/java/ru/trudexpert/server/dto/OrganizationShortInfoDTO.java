package ru.trudexpert.server.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.trudexpert.server.entity.Organization;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class OrganizationShortInfoDTO {

    private Long id;

    private String name;

    public static OrganizationShortInfoDTO getFromEntity(Organization entity){
        if(entity == null){
            return null;
        }

        return new OrganizationShortInfoDTO()
                .setId(entity.getId())
                .setName(entity.getName());
    }
}
