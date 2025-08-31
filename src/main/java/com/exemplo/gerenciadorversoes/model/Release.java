package com.exemplo.gerenciadorversoes.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "releases")
public class Release extends PanacheEntity {

    @NotBlank(message = "Título é obrigatório")
    @Size(max = 200, message = "Título deve ter no máximo 200 caracteres")
    @Column(nullable = false, length = 200)
    public String title;

    @Size(max = 1000, message = "Descrição deve ter no máximo 1000 caracteres")
    @Column(length = 1000)
    public String description;

    @Column(name = "released_at")
    public LocalDateTime releasedAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "version_id", nullable = false)
    public Version version;

    @OneToMany(mappedBy = "release", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<Feature> features;

    @OneToMany(mappedBy = "release", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<BugFix> bugFixes;

    @PrePersist
    public void prePersist() {
        if (releasedAt == null) {
            releasedAt = LocalDateTime.now();
        }
    }

    public static Release findByVersion(Version version) {
        return find("version", version).firstResult();
    }

    public static List<Release> findRecentReleases(int limit) {
        return find("ORDER BY releasedAt DESC").page(0, limit).list();
    }
}
