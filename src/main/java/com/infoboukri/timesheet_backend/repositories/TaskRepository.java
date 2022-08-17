package com.infoboukri.timesheet_backend.repositories;


import com.infoboukri.timesheet_backend.entities.Project;
import com.infoboukri.timesheet_backend.entities.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findByProject_NameEquals(String projectName);
    Page<Task> findAllByOrderByIdDesc(Pageable pageable);
    Page<Task> findByNameContainingOrderByIdDesc(String name, Pageable pageable);

}
