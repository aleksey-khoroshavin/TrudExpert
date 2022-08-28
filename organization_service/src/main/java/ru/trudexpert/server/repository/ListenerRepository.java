package ru.trudexpert.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.trudexpert.server.entity.Listener;

import java.util.List;

@Repository
public interface ListenerRepository extends JpaRepository<Listener, Long> {

    @Query(value = "select * from " +
            "listeners inner join organization_listener ol on listeners.id = ol.listener_id " +
            "where ol.organization_id = ?2 and listeners.surname like ?1", nativeQuery = true)
    List<Listener> findAllBySurnameFromOrganization(String surname, Long organizationId);

    @Query(value = "select * from " +
            "listeners inner join organization_listener ol on listeners.id = ol.listener_id " +
            "where ol.organization_id = ?1", nativeQuery = true)
    List<Listener> findAllFromOrganization(Long organizationId);
}
