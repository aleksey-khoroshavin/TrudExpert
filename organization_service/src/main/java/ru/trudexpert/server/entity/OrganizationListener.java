package ru.trudexpert.server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "organization_listener")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class OrganizationListener {

    @EmbeddedId
    OrganizationListenerKey id;

    @ManyToOne
    @MapsId("listenerId")
    @JoinColumn(name = "listener_id")
    private Listener listener;

    @ManyToOne
    @MapsId("organizationId")
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @Column(name = "post", columnDefinition = "TEXT")
    private String post;

    @Override
    public boolean equals(Object obj) {
        if(obj == null){
            return false;
        }

        if(!(obj instanceof OrganizationListener organizationListener)){
            return false;
        }

        return Objects.equals(this.listener.getId(), organizationListener.getListener().getId()) &&
                Objects.equals(this.organization.getId(), organizationListener.getOrganization().getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getListener().getId(), this.getOrganization().getId());
    }
}
