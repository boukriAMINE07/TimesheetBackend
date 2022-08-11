package com.infoboukri.timesheet_backend.services;

import com.infoboukri.timesheet_backend.entities.Consultant;
import com.infoboukri.timesheet_backend.exceptions.ConsultantNotFoundException;
import com.infoboukri.timesheet_backend.repositories.ConsultantRepository;
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
public class ConsultantServiceImpl implements ConsultantService {
    private ConsultantRepository consultantRepository;
    @Override
    public Consultant saveConsultant(Consultant consultant) {
        return consultantRepository.save(consultant);
    }

    @Override
    public List<Consultant> allConsultant() {
        return consultantRepository.findAll();
    }

    @Override
    public Consultant getConsultant(Long consultantId) throws ConsultantNotFoundException {
        Consultant consultant=consultantRepository.findById(consultantId)
                                                .orElseThrow(()->new ConsultantNotFoundException("Counsultant By Id: "+consultantId+" Not Found"));
        return consultant;
    }

    @Override
    public Consultant updateConsultant(Consultant consultant) {
        return consultantRepository.save(consultant);
    }

    @Override
    public void deleteConsultant(Long consultantId) {
        consultantRepository.deleteById(consultantId);
    }

    @Override
    public Page<Consultant> getAllConsultantWithPage(int page, int size) {
        Page<Consultant> pageConsultants=consultantRepository.findAll(PageRequest.of(page, size));
        return pageConsultants;
    }

    @Override
    public Page<Consultant> getAllConsultantWithNameAndPage(String name, int page, int size) {
        Page<Consultant> pageConsultants=consultantRepository.findByNameContaining(name,PageRequest.of(page, size));
        return pageConsultants;
    }
}
