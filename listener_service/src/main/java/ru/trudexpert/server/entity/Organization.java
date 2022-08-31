package ru.trudexpert.server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.trudexpert.server.dto.entity.OrganizationDTO;

import javax.persistence.*;
import java.util.Set;

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

    @Column(name = "name", columnDefinition = "TEXT", unique = true)
    private String name;

    @Column(name = "law_address", columnDefinition = "TEXT")
    private String lawAddress;

    @Column(name = "fact_address", columnDefinition = "TEXT")
    private String factAddress;

    @Column(name = "phone", columnDefinition = "varchar(50)")
    private String phone;

    @Column(name = "INN", columnDefinition = "varchar(50)")
    private String inn;

    @Column(name = "KPP", columnDefinition = "varchar(50)")
    private String kpp;

    @Column(name = "ORGN", columnDefinition = "varchar(50)")
    private String orgn;

    @Column(name = "checking_account", columnDefinition = "TEXT")
    private String checkingAccount;

    @Column(name = "correspondent_account", columnDefinition = "TEXT")
    private String correspondentAccount;

    @Column(name = "email", columnDefinition = "varchar(50)")
    private String email;

    @Column(name = "BIK", columnDefinition = "varchar(50)")
    private String bik;

    @Column(name = "OKPO", columnDefinition = "varchar(50)")
    private String okpo;

    @Column(name = "OKVED", columnDefinition = "varchar(50)")
    private String okved;

    @OneToOne(mappedBy = "organization", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private OrganizationAgent organizationAgent;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
    private Set<ListenerOrganization> listeners;

    public static Organization getFromDTO(OrganizationDTO dto){
        if(dto == null){
            return null;
        }

        return new Organization()
                .setName(dto.getOrganizationName())
                .setLawAddress(dto.getLawAddress())
                .setFactAddress(dto.getFactAddress())
                .setPhone(dto.getPhone())
                .setInn(dto.getInn())
                .setKpp(dto.getKpp())
                .setOrgn(dto.getOrgn())
                .setCheckingAccount(dto.getCheckingAccount())
                .setCorrespondentAccount(dto.getCorrespondentAccount())
                .setEmail(dto.getEmail())
                .setBik(dto.getBik())
                .setOkpo(dto.getOkpo())
                .setOkved(dto.getOkved());
    }

    public void addListener(Listener listener, String post){
        ListenerOrganization listenerOrganization = new ListenerOrganization();

        listenerOrganization.setId(new OrganizationListenerKey()
                .setOrganizationId(this.id)
                .setListenerId(listener.getId()));

        listenerOrganization.setOrganization(this);
        listenerOrganization.setListener(listener);
        listenerOrganization.setPost(post);

        listener.getOrganizations().add(listenerOrganization);
        this.listeners.add(listenerOrganization);
    }

    public void removeListener(Listener listener){
        ListenerOrganization listenerOrganization = new ListenerOrganization();

        listenerOrganization.setId(new OrganizationListenerKey()
                .setOrganizationId(this.id)
                .setListenerId(listener.getId()));

        listenerOrganization.setOrganization(this);
        listenerOrganization.setListener(listener);

        listener.getOrganizations().remove(listenerOrganization);
        for(ListenerOrganization listenerOrganization1 : listener.getOrganizations()){
            System.out.println(listenerOrganization1.getOrganization().getName());
        }

        this.listeners.remove(listenerOrganization);
    }
}
