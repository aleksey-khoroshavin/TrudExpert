package ru.trudexpert.server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "organizations")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", columnDefinition = "TEXT")
    private String name;

    @Column(name = "law_address", columnDefinition = "TEXT")
    private String lawAddress;

    @Column(name = "fact_address", columnDefinition = "TEXT")
    private String factAddress;

    @Column(name = "phone", precision = 50)
    private String phone;

    @Column(name = "INN", precision = 50)
    private String inn;

    @Column(name = "KPP", precision = 50)
    private String kpp;

    @Column(name = "ORGN", precision = 50)
    private String orgn;

    @Column(name = "checking_account", columnDefinition = "TEXT")
    private String checkingAccount;

    @Column(name = "correspondent_account", columnDefinition = "TEXT")
    private String correspondentAccount;

    @Column(name = "email", precision = 50)
    private String email;

    @Column(name = "BIK", precision = 50)
    private String bik;

    @OneToOne(mappedBy = "organization", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private OrganizationAgent organizationAgent;
}
