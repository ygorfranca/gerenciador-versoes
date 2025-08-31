package com.exemplo.gerenciadorversoes.dto;

import com.exemplo.gerenciadorversoes.model.Project;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ProjectDTO {

    public Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
    public String name;

    @Size(max = 500, message = "Descrição deve ter no máximo 500 caracteres")
    public String description;

    @NotBlank(message = "Slug é obrigatório")
    @Size(max = 50, message = "Slug deve ter no máximo 50 caracteres")
    public String slug;

    public ProjectDTO() {}

    public ProjectDTO(Project project) {
        this.id = project.id;
        this.name = project.name;
        this.description = project.description;
        this.slug = project.slug;
    }

    public Project toEntity() {
        Project project = new Project();
        project.id = this.id;
        project.name = this.name;
        project.description = this.description;
        project.slug = this.slug;
        return project;
    }
}
