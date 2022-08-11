package com.infoboukri.timesheet_backend.services;

import com.infoboukri.timesheet_backend.entities.Consultant;
import com.infoboukri.timesheet_backend.exceptions.ConsultantNotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ConsultantService {
    Consultant saveConsultant(Consultant consultant);
    List<Consultant> allConsultant();
    Consultant getConsultant(Long consultantId) throws ConsultantNotFoundException;
    Consultant updateConsultant(Consultant consultant);
    void deleteConsultant(Long consultantId);
    Page<Consultant> getAllConsultantWithPage(int page, int size);
    Page<Consultant> getAllConsultantWithNameAndPage(String name,int page, int size);
}
