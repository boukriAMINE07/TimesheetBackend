package com.infoboukri.timesheet_backend.services;

import com.infoboukri.timesheet_backend.entities.Project;
import com.infoboukri.timesheet_backend.exceptions.ProjectNotFoundException;
import com.infoboukri.timesheet_backend.repositories.ProjectRepository;
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
public class ProjectServiceImpl implements ProjectService {
    private ProjectRepository projectRepository;
    @Override
    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public List<Project> allProject() {

        return projectRepository.findAll();
    }

    @Override
    public Project getProject(Long projectId) throws ProjectNotFoundException {
        Project project=projectRepository.findById(projectId).orElseThrow(()->new ProjectNotFoundException("Project By Id: "+projectId+" Not Found"));
        return project;
    }

    @Override
    public Project updateProject(Project project) {

        return projectRepository.save(project);
    }

    @Override
    public void deleteProject(Long projectId) {
                projectRepository.deleteById(projectId);
    }

    @Override
    public List<Project> getProjectByName(String name) {
        return projectRepository.findByNameEquals(name);
    }

    @Override
    public Page<Project> getAllProjectWithPage(int page, int size) {
        Page<Project> pageProjects=projectRepository.findAll(PageRequest.of(page, size));

        return pageProjects;
    }

    @Override
    public Page<Project> getAllProjectWithNameAndPage(String name, int page, int size) {
        Page<Project> pageProjects=projectRepository.findByNameContaining(name,PageRequest.of(page, size));
        return pageProjects;
    }
}
