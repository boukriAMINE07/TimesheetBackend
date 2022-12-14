package com.infoboukri.timesheet_backend.web;


import com.infoboukri.timesheet_backend.entities.TaskOfConsultant;

import com.infoboukri.timesheet_backend.enums.State;
import com.infoboukri.timesheet_backend.exceptions.TaskOfConsultantNotFoundException;
import com.infoboukri.timesheet_backend.services.TaskOfConsultantService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
public class TaskOfConsultantRestController {
    private TaskOfConsultantService taskOfConsultantService;

    @GetMapping("/taskOfConsultant")
    public List<TaskOfConsultant> listTaskOfConsultant(){
        return taskOfConsultantService.allTaskOfConsultant();
    }

    @GetMapping("/taskOfConsultant/{id}")
    public TaskOfConsultant getTaskOfConsultant(@PathVariable Long id) throws TaskOfConsultantNotFoundException {
        return taskOfConsultantService.getTaskOfConsultant(id);
    }
    @GetMapping("/taskOfConsultant/searchTask")
    public List<TaskOfConsultant> getTaskOfConsultantBytaskName(@RequestParam(name = "task",defaultValue = "") String task)  {
        return taskOfConsultantService.getTaskOfConsultantByTask_Name(task);
    }
    @GetMapping("/taskOfConsultant/searchConsultantByName")
    public List<TaskOfConsultant> getTaskOfConsultantByConsultant_Name(@RequestParam(name = "consultant",defaultValue = "") String consultant)  {
        return taskOfConsultantService.getTaskOfConsultantByConsultant_Name(consultant);
    }
    @GetMapping("/taskOfConsultant/searchConsultant")
    public ResponseEntity<Map<String,Object>> getTaskOfConsultantByConsultantNameAndPage(  @RequestParam(name = "consultant",defaultValue = "") String consultant
                                                                             ,@RequestParam(name = "page",defaultValue = "0") int page,
                                                                                           @RequestParam(name = "size",defaultValue = "5") int size){
        try {
            List<TaskOfConsultant> taskOfConsultants = new ArrayList<TaskOfConsultant>();
            Page<TaskOfConsultant> pageTaskOfConsultants= taskOfConsultantService.getTaskOfConsultantByConsultant_Name(consultant,page, size);

            taskOfConsultants = pageTaskOfConsultants.getContent();
            Map<String,Object> response=new HashMap<>();
            response.put("taskOfConsultants",taskOfConsultants);
            response.put("currentPage",pageTaskOfConsultants.getNumber());
            response.put("totalItems",pageTaskOfConsultants.getTotalElements());
            response.put("totalPages",pageTaskOfConsultants.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/taskOfConsultant/searchConsultantByNameAndTask")
    public ResponseEntity<Map<String,Object>> getTaskOfConsultantByConsultantNameAndTaskAndPage(  @RequestParam(name = "consultant",defaultValue = "") String consultant,
                                                                                                  @RequestParam(name="task",required = false) String task
            ,@RequestParam(name = "page",defaultValue = "0") int page,
                                                                                                  @RequestParam(name = "size",defaultValue = "5") int size){
        try {
            List<TaskOfConsultant> taskOfConsultants = new ArrayList<TaskOfConsultant>();
            Page<TaskOfConsultant> pageTaskOfConsultants ;
            if (task==null){
                pageTaskOfConsultants= taskOfConsultantService.getAllTaskOfConsultantWithNameOfConsultantAndPage(consultant,page, size);
            }else{
                pageTaskOfConsultants= taskOfConsultantService.getAllTaskOfConsultantWithNameOfConsultantAndTaskNameAndPage(consultant,task,page, size);
            }


            taskOfConsultants = pageTaskOfConsultants.getContent();
            Map<String,Object> response=new HashMap<>();
            response.put("taskOfConsultants",taskOfConsultants);
            response.put("currentPage",pageTaskOfConsultants.getNumber());
            response.put("totalItems",pageTaskOfConsultants.getTotalElements());
            response.put("totalPages",pageTaskOfConsultants.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/taskOfConsultant")
    public TaskOfConsultant saveTaskOfConsultant(@RequestBody TaskOfConsultant taskOfConsultant){
        return taskOfConsultantService.saveTaskOfConsultant(taskOfConsultant);
    }

    @PutMapping("/taskOfConsultant/{id}")
    public TaskOfConsultant updateTaskOfConsultant(@PathVariable Long id,@RequestBody TaskOfConsultant taskOfConsultant){
        taskOfConsultant.setId(id);
        return taskOfConsultantService.updateTaskOfConsultant(taskOfConsultant);
    }
    @DeleteMapping("/taskOfConsultant/{id}")
    public void deleteTaskOfConsultant (@PathVariable Long id){
        taskOfConsultantService.deleteTaskOfConsultant(id);

    }

    @PatchMapping(value = "/taskOfConsultant/{id}")
    public ResponseEntity<TaskOfConsultant> updateTotalHoursTaskOfProject(@PathVariable Long id, @RequestBody Map<Object,Integer> fields) throws TaskOfConsultantNotFoundException {
        TaskOfConsultant taskOfConsultant=taskOfConsultantService.getTaskOfConsultant(id);
        if (taskOfConsultant!=null){
            fields.forEach((key,value)->{
                Field field= ReflectionUtils.findField(TaskOfConsultant.class,(String)key);
                field.setAccessible(true);
                ReflectionUtils.setField(field,taskOfConsultant,value);
            });
            TaskOfConsultant taskOfConsultantUpdate=taskOfConsultantService.updateTaskOfConsultant(taskOfConsultant);
            return new ResponseEntity<>(taskOfConsultantUpdate, HttpStatus.OK);
        }
        return null;

    }

    @GetMapping("/taskOfConsultant/pageTaskOfConsultants")
    public ResponseEntity<Map<String,Object>> listPageTask(  @RequestParam(required = false) String name
            ,@RequestParam(name = "page",defaultValue = "0") int page,
                                                                @RequestParam(name = "size",defaultValue = "5") int size){
        try {
            List<TaskOfConsultant> taskOfConsultants = new ArrayList<TaskOfConsultant>();
            Page<TaskOfConsultant> pageTaskOfConsultants ;
            if (name==null){
                pageTaskOfConsultants= taskOfConsultantService.getAllTaskOfConsultantWithPage(page, size);
            }else{
                pageTaskOfConsultants= taskOfConsultantService.getAllTaskOfConsultantWithNameOfConsultantAndPage(name,page, size);
            }
            taskOfConsultants = pageTaskOfConsultants.getContent();
            Map<String,Object> response=new HashMap<>();
            response.put("taskOfConsultants",taskOfConsultants);
            response.put("currentPage",pageTaskOfConsultants.getNumber());
            response.put("totalItems",pageTaskOfConsultants.getTotalElements());
            response.put("totalPages",pageTaskOfConsultants.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping(value = "/taskOfConsultant/edit/{id}")
    public ResponseEntity<TaskOfConsultant> updateTotalHoursAndStateTaskOfProject(@PathVariable Long id, @RequestBody Map<Object,Object> fields) throws TaskOfConsultantNotFoundException {
        TaskOfConsultant taskOfConsultant=taskOfConsultantService.getTaskOfConsultant(id);
        if (taskOfConsultant!=null){
            fields.forEach((key,value)->{

                if(key=="state"){

                    Field field= ReflectionUtils.findField(TaskOfConsultant.class,(String)key);
                    field.setAccessible(true);
                    ReflectionUtils.setField(field,taskOfConsultant, State.valueOf((String) value));
                }else {
                    Field field= ReflectionUtils.findField(TaskOfConsultant.class,(String)key);
                    field.setAccessible(true);
                    ReflectionUtils.setField(field,taskOfConsultant,value);
                }
            });
            TaskOfConsultant taskOfConsultantUpdate=taskOfConsultantService.updateTaskOfConsultant(taskOfConsultant);
            return new ResponseEntity<>(taskOfConsultantUpdate, HttpStatus.OK);
        }
        return null;

    }

    @GetMapping("/taskOfConsultant/searchByProject")
    public List<TaskOfConsultant> listTaskOfConsultantByProject(@RequestParam(required = false) String name){
        return taskOfConsultantService.getAllTaskOfConsultantByProjectName(name);
    }

    @GetMapping("/taskOfConsultant/pageTaskOfConsultantsByDate")
    public ResponseEntity<Map<String,Object>> listPageTaskByDate(  @RequestParam(required = false) String name,
                                                                   @RequestParam(name="task",required = false) String task,
                                                                   @RequestParam(required = false) String start,
                                                                   @RequestParam(required = false) String end
                                                              ,@RequestParam(name = "page",defaultValue = "0") int page,
                                                                @RequestParam(name = "size",defaultValue = "5") int size){
        try {
            List<TaskOfConsultant> taskOfConsultants = new ArrayList<TaskOfConsultant>();
            Page<TaskOfConsultant> pageTaskOfConsultants ;
            SimpleDateFormat formatter = new SimpleDateFormat("yyy-MM-dd", Locale.ENGLISH);
            if (name==null && task==null && start==null && end==null){
                pageTaskOfConsultants= taskOfConsultantService.getAllTaskOfConsultantWithPage(page, size);

            }else if (task==null && start==null && end==null){
                pageTaskOfConsultants= taskOfConsultantService.getAllTaskOfConsultantWithNameOfConsultantAndPage(name,page, size);
            }
            else if ( start==null && end==null){
                pageTaskOfConsultants= taskOfConsultantService.getAllTaskOfConsultantWithNameOfConsultantAndTaskNameAndPage(name,task,page, size);
            } else if(task==null && end==null){
                pageTaskOfConsultants= taskOfConsultantService.getAllTaskOfConsultantWithNameOfConsultantAndDateAndPage(name, formatter.parse(start),page, size);
            } else if(task==null){
                pageTaskOfConsultants= taskOfConsultantService.getAllTaskOfConsultantWithNameOfConsultantAndDateAndPage(name, formatter.parse(start),page, size);
            }


            else{
                pageTaskOfConsultants= taskOfConsultantService.getAllTaskOfConsultantWithNameOfConsultantAndTaskNameAndDateBetweenAndPage(name, task,formatter.parse(start),formatter.parse(end),page, size);
            }
            taskOfConsultants = pageTaskOfConsultants.getContent();
            Map<String,Object> response=new HashMap<>();
            response.put("taskOfConsultants",taskOfConsultants);
            response.put("currentPage",pageTaskOfConsultants.getNumber());
            response.put("totalItems",pageTaskOfConsultants.getTotalElements());
            response.put("totalPages",pageTaskOfConsultants.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
