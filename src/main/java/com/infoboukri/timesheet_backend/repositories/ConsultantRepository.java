package com.infoboukri.timesheet_backend.repositories;

import com.infoboukri.timesheet_backend.entities.Consultant;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultantRepository extends JpaRepository<Consultant,Long> {
    Page<Consultant> findAll(Pageable pageable);
    Page<Consultant> findByNameContaining(String name, Pageable pageable);
}
