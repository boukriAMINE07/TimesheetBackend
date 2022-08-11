package com.infoboukri.timesheet_backend.securite.repository;


import com.infoboukri.timesheet_backend.securite.models.ERole;
import com.infoboukri.timesheet_backend.securite.models.Role;
import com.infoboukri.timesheet_backend.securite.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);

  Page<User> findAll(Pageable pageable);
  Page<User> findByUsernameContaining(String name, Pageable pageable);

  //Page<User> findByRolesName(String name , Pageable pageable);
  List<User> findByRolesNameIn(Collection<ERole> names);
  Page<User> findByRolesNameIn(Collection<ERole> names, Pageable pageable);

}
