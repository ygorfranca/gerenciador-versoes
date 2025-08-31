package com.exemplo.gerenciadorversoes.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "projects")
@Data
@EqualsAndHashCode(callSuper = true, exclude = {"versions"})
@ToString(exclude = {"versions"})
public class Project extends PanacheEntity {

    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
    @Column(nullable = false, length = 100)
    public String name;

    @Size(max = 500, message = "Descrição deve ter no máximo 500 caracteres")
    @Column(length = 500)
    public String description;

    @NotBlank(message = "Slug é obrigatório")
    @Size(max = 50, message = "Slug deve ter no máximo 50 caracteres")
    @Column(nullable = false, unique = true, length = 50)
    public String slug;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<Version> versions;

    public static Project findBySlug(String slug) {
        return find("slug", slug).firstResult();
    }

    public static List<Project> findByNameContaining(String name) {
        return find("UPPER(name) LIKE UPPER(?1)", "%" + name + "%").list();
    }
}
