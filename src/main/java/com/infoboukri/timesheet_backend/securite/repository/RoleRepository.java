package com.infoboukri.timesheet_backend.securite.repository;

import com.infoboukri.timesheet_backend.securite.models.ERole;
import com.infoboukri.timesheet_backend.securite.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
