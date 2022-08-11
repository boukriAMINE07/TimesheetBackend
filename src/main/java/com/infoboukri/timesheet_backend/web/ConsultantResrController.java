package com.infoboukri.timesheet_backend.web;


import com.infoboukri.timesheet_backend.entities.Consultant;

import com.infoboukri.timesheet_backend.entities.Project;
import com.infoboukri.timesheet_backend.exceptions.ConsultantNotFoundException;
import com.infoboukri.timesheet_backend.exceptions.ProjectNotFoundException;
import com.infoboukri.timesheet_backend.services.ConsultantService;
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
public class ConsultantResrController {
    private ConsultantService consultantService;

    @GetMapping("/consultants")
    public List<Consultant> listConsultant(){
        return consultantService.allConsultant();
    }

    @GetMapping("/consultants/{consultant_id}")
    public Consultant getConsultant(@PathVariable Long consultant_id) throws ConsultantNotFoundException {
        return consultantService.getConsultant(consultant_id);
    }

    @PostMapping("/consultants")
    public Consultant saveConsultant(@RequestBody  Consultant consultant){
        return consultantService.saveConsultant(consultant);
    }

    @PutMapping("/consultants/{consultant_id}")
    public Consultant updateConsultant(@PathVariable Long consultant_id, @RequestBody Consultant consultant){
        consultant.setConsultant_id(consultant_id);
        return consultantService.updateConsultant(consultant);
    }
    @DeleteMapping("/consultants/{consultant_id}")
    public void deleteCustomer (@PathVariable Long consultant_id){
        consultantService.deleteConsultant(consultant_id);

    }

    @GetMapping("/consultants/pageConsultants")
    public ResponseEntity<Map<String,Object>> listPageProject(@RequestParam(required = false) String name
                                                            , @RequestParam(name = "page",defaultValue = "0") int page,
                                                              @RequestParam(name = "size",defaultValue = "5") int size){
        try {
            List<Consultant> consultants = new ArrayList<Consultant>();
            Page<Consultant> pageConsultants ;
            if (name==null){
                pageConsultants= consultantService.getAllConsultantWithPage(page, size);
            }else{
                pageConsultants= consultantService.getAllConsultantWithNameAndPage(name,page, size);
            }
            consultants = pageConsultants.getContent();
            Map<String,Object> response=new HashMap<>();
            response.put("consultants",consultants);
            response.put("currentPage",pageConsultants.getNumber());
            response.put("totalItems",pageConsultants.getTotalElements());
            response.put("totalPages",pageConsultants.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
