package com.infoboukri.timesheet_backend.repositories;

import com.infoboukri.timesheet_backend.entities.Task;
import com.infoboukri.timesheet_backend.entities.TaskOfConsultant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskOfConsultantRepository extends JpaRepository<TaskOfConsultant,Long> {

    List<TaskOfConsultant> findTaskOfConsultantByTaskNameEquals(String taskName);
    List<TaskOfConsultant> findTaskOfConsultantByUserUsernameEquals(String consultantName);
    Page<TaskOfConsultant> findAll(Pageable pageable);
    Page<TaskOfConsultant> findTaskOfConsultantByUserUsernameContaining(String name, Pageable pageable);

}
