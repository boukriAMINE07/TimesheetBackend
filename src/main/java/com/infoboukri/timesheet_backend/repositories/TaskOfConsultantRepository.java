package com.infoboukri.timesheet_backend.repositories;

import com.infoboukri.timesheet_backend.entities.Task;
import com.infoboukri.timesheet_backend.entities.TaskOfConsultant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface TaskOfConsultantRepository extends JpaRepository<TaskOfConsultant,Long> {

    List<TaskOfConsultant> findTaskOfConsultantByTaskNameEquals(String taskName);
    Page<TaskOfConsultant> findTaskOfConsultantByUserUsernameEqualsOrderByIdDesc(String consultantName,Pageable pageable);
    Page<TaskOfConsultant> findTaskOfConsultantByUserUsernameEqualsAndTaskNameContainingOrderByIdDesc(String consultantName,String taskName,Pageable pageable);
    Page<TaskOfConsultant> findTaskOfConsultantByUserUsernameEqualsAndStartDateAfterOrderByIdDesc(String consultantName, Date dateBefore, Pageable pageable);
    @Query("select task from TaskOfConsultant task where task.user.username=:name and task.startDate>=:start and task.endDate<=:end")
    Page<TaskOfConsultant> findTaskOfConsultantByUserUsernameAndAndDateBetween(@Param("name") String name,@Param("start") Date start,@Param("end") Date end, Pageable pageable);
    List<TaskOfConsultant> findTaskOfConsultantByUserUsernameEquals(String consultantName);
    List<TaskOfConsultant> findTaskOfConsultantByTaskProjectNameEquals(String projectName);
    Page<TaskOfConsultant> findAllByOrderByIdDesc(Pageable pageable);
    Page<TaskOfConsultant> findTaskOfConsultantByUserUsernameContainingOrderByIdDesc(String name, Pageable pageable);

}
