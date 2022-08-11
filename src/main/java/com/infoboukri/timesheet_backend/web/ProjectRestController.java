package com.infoboukri.timesheet_backend.web;

import com.infoboukri.timesheet_backend.entities.Project;
import com.infoboukri.timesheet_backend.exceptions.ProjectNotFoundException;
import com.infoboukri.timesheet_backend.services.ProjectService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.*;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
public class ProjectRestController {
    private ProjectService projectService;

    @GetMapping("/projects")
    public List<Project> listProject(){
        return projectService.allProject();
    }
    @GetMapping("/projects/{project_id}")
    public Project getProject(@PathVariable Long project_id) throws ProjectNotFoundException {
        return projectService.getProject(project_id);
    }
    @GetMapping("/projects/search")
    public List<Project> getProjectByName(@RequestParam(name = "name",defaultValue = "") String name)  {
        return projectService.getProjectByName(name);
    }
    @PostMapping("/projects")
    public Project saveProject(@RequestBody Project project){
        return projectService.saveProject(project);
    }

    @PutMapping("/projects/{project_id}")
    public Project updateProject(@PathVariable Long project_id,@RequestBody Project project){
        project.setProject_id(project_id);
        return projectService.updateProject(project);
    }
    @PatchMapping(value = "/projects/{project_id}")
    public ResponseEntity<Project>  updateTotalHoursOfProject(@PathVariable Long project_id, @RequestBody Map<Object,Integer> fields) throws ProjectNotFoundException {
        Project project=projectService.getProject(project_id);
        if (project!=null){
            fields.forEach((key,value)->{
                Field field= ReflectionUtils.findField(Project.class,(String)key);
                field.setAccessible(true);
                ReflectionUtils.setField(field,project,value);
            });
            Project projectUpdate=projectService.updateProject(project);
            return new ResponseEntity<>(projectUpdate,HttpStatus.OK);
        }
      return null;

    }
    @DeleteMapping("/projects/{project_id}")
    public void deleteCustomer (@PathVariable Long project_id){
        projectService.deleteProject(project_id);

    }

    @GetMapping("/projects/pageProjects")
    public ResponseEntity<Map<String,Object>> listPageProject(  @RequestParam(required = false) String name
                                                                ,@RequestParam(name = "page",defaultValue = "0") int page,
                                                                 @RequestParam(name = "size",defaultValue = "5") int size){
        try {
            List<Project> projects = new ArrayList<Project>();
            Page<Project> pageProjects ;
            if (name==null){
                pageProjects= projectService.getAllProjectWithPage(page, size);
            }else{
                pageProjects= projectService.getAllProjectWithNameAndPage(name,page, size);
            }
            projects = pageProjects.getContent();
            Map<String,Object> response=new HashMap<>();
            response.put("projects",projects);
            response.put("currentPage",pageProjects.getNumber());
            response.put("totalItems",pageProjects.getTotalElements());
            response.put("totalPages",pageProjects.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
