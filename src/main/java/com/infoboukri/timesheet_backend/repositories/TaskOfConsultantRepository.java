package com.infoboukri.timesheet_backend.repositories;

import com.infoboukri.timesheet_backend.entities.Task;
import com.infoboukri.timesheet_backend.entities.TaskOfConsultant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskOfConsultantRepository extends JpaRepository<TaskOfConsultant,Long> {

    List<TaskOfConsultant> findTaskOfConsultantByTaskNameEquals(String taskName);
    Page<TaskOfConsultant> findTaskOfConsultantByUserUsernameEqualsOrderByIdDesc(String consultantName,Pageable pageable);
    Page<TaskOfConsultant> findTaskOfConsultantByUserUsernameEqualsAndTaskNameContainingOrderByIdDesc(String consultantName,String taskName,Pageable pageable);
    List<TaskOfConsultant> findTaskOfConsultantByUserUsernameEquals(String consultantName);
    Page<TaskOfConsultant> findAllByOrderByIdDesc(Pageable pageable);
    Page<TaskOfConsultant> findTaskOfConsultantByUserUsernameContainingOrderByIdDesc(String name, Pageable pageable);

}
