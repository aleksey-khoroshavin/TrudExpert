package ru.trudexpert.server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

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

    @Column(name = "surname", precision = 100)
    private String surname;

    @Column(name = "name", precision = 80)
    private String name;

    @Column(name = "patronymic", precision = 100)
    private String patronymic;

    @Column(name = "document", columnDefinition = "TEXT")
    private String document;

    @Column(name = "post", columnDefinition = "TEXT")
    private String post;

    @OneToOne
    @MapsId
    @JoinColumn(name = "organization_id")
    private Organization organization;
}
