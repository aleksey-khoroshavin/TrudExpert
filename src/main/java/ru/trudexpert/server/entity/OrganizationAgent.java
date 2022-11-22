package ru.trudexpert.server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.trudexpert.server.dto.entity.OrganizationAgentDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "organization_agent")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class OrganizationAgent {
    @Id
    @Column(name = "organization_id")
    private Long id;

    @Column(name = "surname", columnDefinition = "varchar(100)")
    private String surname;

    @Column(name = "name", columnDefinition = "varchar(80)")
    private String name;

    @Column(name = "patronymic", columnDefinition = "varchar(100)")
    private String patronymic;

    @Column(name = "document", columnDefinition = "TEXT")
    private String document;

    @Column(name = "post", columnDefinition = "TEXT")
    private String post;

    @OneToOne
    @MapsId
    @JoinColumn(name = "organization_id")
    private Organization organization;

    public static OrganizationAgent getFromDTO(OrganizationAgentDTO dto) {
        if (dto == null) {
            return null;
        }

        return new OrganizationAgent()
                .setSurname(dto.getSurname())
                .setName(dto.getName())
                .setPatronymic(dto.getPatronymic())
                .setDocument(dto.getDocument())
                .setPost(dto.getPost());
    }
}
