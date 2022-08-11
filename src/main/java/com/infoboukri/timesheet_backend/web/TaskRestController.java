package com.infoboukri.timesheet_backend.web;

import com.infoboukri.timesheet_backend.entities.Project;
import com.infoboukri.timesheet_backend.entities.Task;
import com.infoboukri.timesheet_backend.exceptions.TaskNotFoundException;
import com.infoboukri.timesheet_backend.services.TaskService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
public class TaskRestController {
    private TaskService taskService;


    @GetMapping("/tasks")
    public List<Task> listTask(){
        return taskService.allTask();
    }

    @GetMapping("/tasks/{task_id}")
    public Task getTask(@PathVariable Long task_id) throws TaskNotFoundException {
        return taskService.getTask(task_id);
    }

    @GetMapping("/tasks/search")
    public List<Task> getTaskByProjectName(@RequestParam(name = "name",defaultValue = "") String name)  {
        return taskService.getTaskByProjectNameEquals(name);
    }

    @PostMapping("/tasks")
    public Task saveTask(@RequestBody Task task){
        return taskService.saveTask(task);
    }
    @PutMapping("/tasks/{task_id}")
    public Task updateTask(@PathVariable Long task_id,@RequestBody Task task){
        task.setTask_id(task_id);
        return taskService.updateTask(task);
    }
    @DeleteMapping("/tasks/{task_id}")
    public void deleteTask(@PathVariable Long task_id){
        taskService.deleteTask(task_id);

    }


    @GetMapping("/tasks/pageTasks")
    public ResponseEntity<Map<String,Object>> listPageProject(@RequestParam(required = false) String name
                                                             , @RequestParam(name = "page",defaultValue = "0") int page,
                                                              @RequestParam(name = "size",defaultValue = "5") int size){
        try {
            List<Task> tasks = new ArrayList<Task>();
            Page<Task> pageTasks ;
            if (name==null){
                pageTasks= taskService.getAllTaskWithPage(page, size);
            }else{
                pageTasks= taskService.getAllTaskWithNameAndPage(name,page, size);
            }
            tasks = pageTasks.getContent();
            Map<String,Object> response=new HashMap<>();
            response.put("tasks",tasks);
            response.put("currentPage",pageTasks.getNumber());
            response.put("totalItems",pageTasks.getTotalElements());
            response.put("totalPages",pageTasks.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }





}
