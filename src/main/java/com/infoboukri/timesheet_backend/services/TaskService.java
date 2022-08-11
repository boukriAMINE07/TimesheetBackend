package com.infoboukri.timesheet_backend.services;


import com.infoboukri.timesheet_backend.entities.Task;
import com.infoboukri.timesheet_backend.exceptions.TaskNotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TaskService {
    Task saveTask(Task task);
    List<Task> allTask();
    Task getTask(Long taskId) throws TaskNotFoundException;
    Task updateTask(Task task);
    void deleteTask(Long taskId);
    List<Task> getTaskByProjectNameEquals(String name);
    Page<Task> getAllTaskWithPage(int page, int size);
    Page<Task> getAllTaskWithNameAndPage(String name,int page, int size);
}
