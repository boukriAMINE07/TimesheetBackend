package com.infoboukri.timesheet_backend.services;

import com.infoboukri.timesheet_backend.entities.TaskOfConsultant;
import com.infoboukri.timesheet_backend.exceptions.TaskOfConsultantNotFoundException;
import com.infoboukri.timesheet_backend.repositories.TaskOfConsultantRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class TaskOfConsultantServiceImpl implements TaskOfConsultantService {

    private TaskOfConsultantRepository taskOfConsultantRepository;

    @Override
    public TaskOfConsultant saveTaskOfConsultant(TaskOfConsultant taskOfConsultant) {
        return taskOfConsultantRepository.save(taskOfConsultant);
    }

    @Override
    public List<TaskOfConsultant> allTaskOfConsultant() {
        return taskOfConsultantRepository.findAll();
    }

    @Override
    public TaskOfConsultant getTaskOfConsultant(Long taskOfConsultantId) throws TaskOfConsultantNotFoundException {
        TaskOfConsultant taskOfConsultant=taskOfConsultantRepository.findById(taskOfConsultantId).orElseThrow(()->new TaskOfConsultantNotFoundException("Task Of consultant By Id: "+taskOfConsultantId+" Not Found !"));
        return taskOfConsultant;
    }

    @Override
    public List<TaskOfConsultant> getTaskOfConsultantByTask_Name(String taskName) {
        return taskOfConsultantRepository.findTaskOfConsultantByTaskNameEquals(taskName);
    }

    @Override
    public List<TaskOfConsultant> getTaskOfConsultantByConsultant_Name(String consultantName) {
        return taskOfConsultantRepository.findTaskOfConsultantByUserUsernameEquals(consultantName);
    }

    @Override
    public TaskOfConsultant updateTaskOfConsultant(TaskOfConsultant taskOfConsultant) {
        return taskOfConsultantRepository.save(taskOfConsultant);
    }

    @Override
    public void deleteTaskOfConsultant(Long taskOfConsultantId) {
                taskOfConsultantRepository.deleteById(taskOfConsultantId);
    }

    @Override
    public Page<TaskOfConsultant> getAllTaskOfConsultantWithPage(int page, int size) {
        Page<TaskOfConsultant> pageTaskOfConsultants=taskOfConsultantRepository.findAll(PageRequest.of(page, size));
        return pageTaskOfConsultants;
    }

    @Override
    public Page<TaskOfConsultant> getAllTaskOfConsultantWithNameOfConsultantAndPage(String name, int page, int size) {
        Page<TaskOfConsultant> pageTaskOfConsultants=taskOfConsultantRepository.
                findTaskOfConsultantByUserUsernameContaining(name,PageRequest.of(page, size));

        return pageTaskOfConsultants;
    }
}
