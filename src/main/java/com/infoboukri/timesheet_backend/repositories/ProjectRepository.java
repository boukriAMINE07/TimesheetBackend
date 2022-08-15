package com.infoboukri.timesheet_backend.repositories;

import com.infoboukri.timesheet_backend.entities.Project;
import com.infoboukri.timesheet_backend.entities.TaskOfConsultant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project,Long> {

    List<Project> findByNameEquals(String name);
    Page<Project> findAllByOrderByIdDesc(Pageable pageable);
    Page<Project> findByNameContainingOrderByIdDesc(String name, Pageable pageable);
}
