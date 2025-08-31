package com.exemplo.gerenciadorversoes.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "features")
public class Feature extends PanacheEntity {

    @NotBlank(message = "Título é obrigatório")
    @Size(max = 200, message = "Título deve ter no máximo 200 caracteres")
    @Column(nullable = false, length = 200)
    public String title;

    @Size(max = 1000, message = "Descrição deve ter no máximo 1000 caracteres")
    @Column(length = 1000)
    public String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "release_id", nullable = false)
    public Release release;

    public static List<Feature> findByRelease(Release release) {
        return find("release", release).list();
    }

    public static List<Feature> findByTitleContaining(String title) {
        return find("UPPER(title) LIKE UPPER(?1)", "%" + title + "%").list();
    }
}
