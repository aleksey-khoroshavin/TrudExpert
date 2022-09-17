package ru.trudexpert.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.trudexpert.server.entity.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

}
