package ru.trudexpert.server.dto.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.trudexpert.server.entity.ListenerOrganization;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class ListenerOrganizationDTO {
    private Long organizationId;
    private String organizationName;
    private String post;

    public static ListenerOrganizationDTO getFromEntity(ListenerOrganization listenerOrganization){
        if(listenerOrganization == null){
            return null;
        }

        return new ListenerOrganizationDTO()
                .setOrganizationId(listenerOrganization.getOrganization().getId())
                .setOrganizationName(listenerOrganization.getOrganization().getName())
                .setPost(listenerOrganization.getPost());
    }
}
