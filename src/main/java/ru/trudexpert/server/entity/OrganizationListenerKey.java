package ru.trudexpert.server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@Embeddable
public class OrganizationListenerKey implements Serializable {

    @Column(name = "listener_id")
    private Long listenerId;

    @Column(name = "organization_id")
    private Long organizationId;

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof OrganizationListenerKey organizationListenerKey)) {
            return false;
        }

        return Objects.equals(this.listenerId, organizationListenerKey.getListenerId()) &&
                Objects.equals(this.organizationId, organizationListenerKey.getOrganizationId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getListenerId(), this.getOrganizationId());
    }
}
