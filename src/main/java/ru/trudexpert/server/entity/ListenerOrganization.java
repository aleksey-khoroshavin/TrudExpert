package ru.trudexpert.server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "organization_listener")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class ListenerOrganization {

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
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof ListenerOrganization listenerOrganization)) {
            return false;
        }

        return Objects.equals(this.listener.getId(), listenerOrganization.getListener().getId()) &&
                Objects.equals(this.organization.getId(), listenerOrganization.getOrganization().getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getListener().getId(), this.getOrganization().getId());
    }
}
