package com.infoboukri.timesheet_backend.web;


import com.infoboukri.timesheet_backend.entities.Consultant;
import com.infoboukri.timesheet_backend.securite.models.ERole;
import com.infoboukri.timesheet_backend.securite.models.User;
import com.infoboukri.timesheet_backend.securite.repository.UserRepository;
import com.infoboukri.timesheet_backend.services.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@CrossOrigin("*")
public class UserRestController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/users")
    public List<User> listUsers(){
        return userService.allUsers();
    }

    @GetMapping("/users/usersByRoleUser")
    public List<User> listUsersByRole( ){
        return  userRepository.findByRolesNameIn(Arrays.asList(ERole.ROLE_USER));
    }
    @GetMapping("/users/usersByRole")
    public ResponseEntity<Map<String,Object>> listPageUsersByRole(@RequestParam(required = false) ERole userRole
            , @RequestParam(name = "page",defaultValue = "0") int page,
                                                            @RequestParam(name = "size",defaultValue = "5") int size){
        try {
            List<User> users = new ArrayList<User>();
            Page<User> pageUsers ;
            if (userRole==null){
                pageUsers= userService.getAllUsersWithPage(page, size);
            }else{
                pageUsers= userRepository.findByRolesNameIn(Arrays.asList(userRole), PageRequest.of(page,size));
            }
            users = pageUsers.getContent();
            Map<String,Object> response=new HashMap<>();
            response.put("users",users);
            response.put("currentPage",pageUsers.getNumber());
            response.put("totalItems",pageUsers.getTotalElements());
            response.put("totalPages",pageUsers.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/users/pageUsers")
    public ResponseEntity<Map<String,Object>> listPageUsers(@RequestParam(required = false) String name
                                                          , @RequestParam(name = "page",defaultValue = "0") int page,
                                                            @RequestParam(name = "size",defaultValue = "5") int size){
        try {
            List<User> users = new ArrayList<User>();
            Page<User> pageUsers ;
            if (name==null){
                pageUsers= userService.getAllUsersWithPage(page, size);
            }else{
                pageUsers= userService.getAllUsersByUserNameAndPage(name,page, size);
            }
            users = pageUsers.getContent();
            Map<String,Object> response=new HashMap<>();
            response.put("users",users);
            response.put("currentPage",pageUsers.getNumber());
            response.put("totalItems",pageUsers.getTotalElements());
            response.put("totalPages",pageUsers.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}