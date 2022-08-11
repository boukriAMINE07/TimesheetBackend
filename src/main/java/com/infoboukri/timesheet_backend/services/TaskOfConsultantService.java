package com.infoboukri.timesheet_backend.services;

import com.infoboukri.timesheet_backend.entities.Task;
import com.infoboukri.timesheet_backend.entities.TaskOfConsultant;
import com.infoboukri.timesheet_backend.exceptions.TaskOfConsultantNotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TaskOfConsultantService {
    TaskOfConsultant saveTaskOfConsultant(TaskOfConsultant taskOfConsultant);
    List<TaskOfConsultant> allTaskOfConsultant();
    TaskOfConsultant getTaskOfConsultant(Long taskOfConsultantId) throws TaskOfConsultantNotFoundException;
    List<TaskOfConsultant> getTaskOfConsultantByTask_Name(String task_Name);
    List<TaskOfConsultant> getTaskOfConsultantByConsultant_Name(String consultant_Name);
    TaskOfConsultant updateTaskOfConsultant(TaskOfConsultant taskOfConsultant);
    void deleteTaskOfConsultant(Long taskOfConsultantId);
    Page<TaskOfConsultant> getAllTaskOfConsultantWithPage(int page, int size);
    Page<TaskOfConsultant> getAllTaskOfConsultantWithNameOfConsultantAndPage(String name,int page, int size);
}
