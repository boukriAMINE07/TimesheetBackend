package com.infoboukri.timesheet_backend.services;

import com.infoboukri.timesheet_backend.entities.Project;
import com.infoboukri.timesheet_backend.entities.Task;
import com.infoboukri.timesheet_backend.exceptions.TaskNotFoundException;
import com.infoboukri.timesheet_backend.repositories.TaskRepository;
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
public class TaskServiceImpl implements TaskService {
    private TaskRepository taskRepository;
    @Override
    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public List<Task> allTask() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTask(Long taskId) throws TaskNotFoundException {
        Task task=taskRepository.findById(taskId).orElseThrow(()->new TaskNotFoundException("Task By Id: "+taskId+" Not Found"));
        return task;
    }

    @Override
    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long taskId) {
                taskRepository.deleteById(taskId);
    }

    @Override
    public List<Task> getTaskByProjectNameEquals(String name) {
        return taskRepository.findByProject_NameEquals(name);
    }

    @Override
    public Page<Task> getAllTaskWithPage(int page, int size) {
        Page<Task> pageTasks=taskRepository.findAllByOrderByIdDesc(PageRequest.of(page, size));
        return pageTasks;
    }

    @Override
    public Page<Task> getAllTaskWithNameAndPage(String name, int page, int size) {
        Page<Task> pageTasks=taskRepository.findByNameContainingOrderByIdDesc(name,PageRequest.of(page, size));
        return pageTasks;
    }
}
