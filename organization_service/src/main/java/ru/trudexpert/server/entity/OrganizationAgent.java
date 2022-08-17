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
}