package com.infoboukri.timesheet_backend.services;

import com.infoboukri.timesheet_backend.entities.Project;
import com.infoboukri.timesheet_backend.exceptions.ProjectNotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProjectService {
    Project saveProject(Project project);
    List<Project> allProject();
    Project getProject(Long projectId) throws ProjectNotFoundException;
    Project updateProject(Project project);
    void deleteProject(Long projectId);
    List<Project> getProjectByName(String name);
    Page<Project> getAllProjectWithPage(int page, int size);
    Page<Project> getAllProjectWithNameAndPage(String name,int page, int size);
}
