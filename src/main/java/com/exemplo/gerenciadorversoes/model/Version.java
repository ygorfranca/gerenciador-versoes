package com.exemplo.gerenciadorversoes.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "versions", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"project_id", "major", "minor", "patch"})
})
public class Version extends PanacheEntity {

    @NotNull(message = "Major é obrigatório")
    @Min(value = 0, message = "Major deve ser maior ou igual a 0")
    @Column(nullable = false)
    public Integer major;

    @NotNull(message = "Minor é obrigatório")
    @Min(value = 0, message = "Minor deve ser maior ou igual a 0")
    @Column(nullable = false)
    public Integer minor;

    @NotNull(message = "Patch é obrigatório")
    @Min(value = 0, message = "Patch deve ser maior ou igual a 0")
    @Column(nullable = false)
    public Integer patch;

    @NotNull(message = "Status é obrigatório")
    @Pattern(regexp = "draft|published|deprecated", message = "Status deve ser: draft, published ou deprecated")
    @Column(nullable = false, length = 20)
    public String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    public Project project;

    @OneToOne(mappedBy = "version", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Release release;

    public String getVersionString() {
        return major + "." + minor + "." + patch;
    }

    public static Version findByProjectAndVersion(Project project, Integer major, Integer minor, Integer patch) {
        return find("project = ?1 and major = ?2 and minor = ?3 and patch = ?4", 
                   project, major, minor, patch).firstResult();
    }

    public static java.util.List<Version> findByProject(Project project) {
        return find("project", project).list();
    }

    public static java.util.List<Version> findByStatus(String status) {
        return find("status", status).list();
    }
}
