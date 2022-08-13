package com.infoboukri.timesheet_backend;

import com.infoboukri.timesheet_backend.entities.Consultant;
import com.infoboukri.timesheet_backend.entities.Project;
import com.infoboukri.timesheet_backend.entities.Task;
import com.infoboukri.timesheet_backend.entities.TaskOfConsultant;
import com.infoboukri.timesheet_backend.enums.State;
import com.infoboukri.timesheet_backend.exceptions.UserNotFoundException;
import com.infoboukri.timesheet_backend.securite.models.ERole;
import com.infoboukri.timesheet_backend.securite.models.Role;
import com.infoboukri.timesheet_backend.securite.models.User;
import com.infoboukri.timesheet_backend.securite.repository.RoleRepository;
import com.infoboukri.timesheet_backend.securite.repository.UserRepository;
import com.infoboukri.timesheet_backend.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

@SpringBootApplication
public class TimesheetBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(TimesheetBackendApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ProjectService projectService
                                        , TaskService taskService,
                                        ConsultantService consultantService,
                                        TaskOfConsultantService taskOfConsultantService, UserRepository userRepository, UserService userService, RoleRepository roleRepository){
        return args -> {
         /*   Role role = new Role();
            role.setName(ERole.ROLE_ADMIN);
            roleRepository.save(role);
            Set<Role> roles = new HashSet<Role>();
            roles.add(role);
            User user=new User();
            user.setEmail("hanane@gmail.com");
            user.setPassword("1234");
            user.setUsername("hanane");
            user.setRoles(roles);
           userService.SaveUser(user);*/

            Stream.of("project 1","project 2","project 3").forEach(project->{
                Project projectSaved=new Project();
                projectSaved.setName(project);
                projectSaved.setDescription(project+" Description");
                projectSaved.setTotalHours((int) (Math.random()*1000));
                projectSaved.setStartDate(new Date());
                projectSaved.setEndDate(new Date());
               // projectService.saveProject(projectSaved);
            });
            projectService.allProject().forEach(project -> {
            Stream.of("coding","deployment","correction bug").forEach(name->{
                Task task=new Task();
                task.setName(name);
                task.setDescription(name+" Description");
                task.setProject(project);
               //taskService.saveTask(task);
            });
            });

            Stream.of("Boukri","AbouFalah","Elmtougui").forEach(name->{
                Consultant consultant=new Consultant();
                consultant.setName(name);
                consultant.setEmail(name+"@gmail.com");
                consultant.setPassword(name);
                consultant.setPhone(63339393L);

               //consultantService.saveConsultant(consultant);
            });
            /*Stream.of("Boukri","Abdo","Hanane").forEach(name->{
                User user=new User();
                user.setUsername(name);
                user.setEmail(name+"@gmail.com");
                user.setPassword(name);

            });*/
            //Consultant consultant=consultantService.getConsultant(2L);





        };






    }

}
