package com.exemplo.gerenciadorversoes.service;

import com.exemplo.gerenciadorversoes.dto.ProjectDTO;
import com.exemplo.gerenciadorversoes.model.Project;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ProjectService {

    @Transactional
    public ProjectDTO create(ProjectDTO projectDTO) {
        validateUniqueSlug(projectDTO.slug, null);
        
        Project project = projectDTO.toEntity();
        project.persist();
        
        return new ProjectDTO(project);
    }

    public ProjectDTO findById(Long id) {
        Project project = Project.findById(id);
        if (project == null) {
            throw new NotFoundException("Projeto não encontrado com ID: " + id);
        }
        return new ProjectDTO(project);
    }

    public ProjectDTO findBySlug(String slug) {
        Project project = Project.findBySlug(slug);
        if (project == null) {
            throw new NotFoundException("Projeto não encontrado com slug: " + slug);
        }
        return new ProjectDTO(project);
    }

    public List<ProjectDTO> findAll() {
        return Project.listAll().stream()
            .map(project -> new ProjectDTO((Project) project))
            .collect(Collectors.toList());
    }

    public List<ProjectDTO> findByName(String name) {
        return Project.findByNameContaining(name).stream()
            .map(ProjectDTO::new)
            .collect(Collectors.toList());
    }

    @Transactional
    public ProjectDTO update(Long id, ProjectDTO projectDTO) {
        Project project = Project.findById(id);
        if (project == null) {
            throw new NotFoundException("Projeto não encontrado com ID: " + id);
        }

        validateUniqueSlug(projectDTO.slug, id);

        project.name = projectDTO.name;
        project.description = projectDTO.description;
        project.slug = projectDTO.slug;

        return new ProjectDTO(project);
    }

    @Transactional
    public void delete(Long id) {
        Project project = Project.findById(id);
        if (project == null) {
            throw new NotFoundException("Projeto não encontrado com ID: " + id);
        }
        project.delete();
    }

    private void validateUniqueSlug(String slug, Long excludeId) {
        Project existingProject = Project.findBySlug(slug);
        if (existingProject != null && (excludeId == null || !existingProject.id.equals(excludeId))) {
            throw new IllegalArgumentException("Slug já existe: " + slug);
        }
    }
}
