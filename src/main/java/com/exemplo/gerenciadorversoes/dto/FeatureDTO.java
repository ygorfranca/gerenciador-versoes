package com.exemplo.gerenciadorversoes.dto;

import com.exemplo.gerenciadorversoes.model.Feature;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class FeatureDTO {

    public Long id;

    @NotBlank(message = "Título é obrigatório")
    @Size(max = 200, message = "Título deve ter no máximo 200 caracteres")
    public String title;

    @Size(max = 1000, message = "Descrição deve ter no máximo 1000 caracteres")
    public String description;

    @NotNull(message = "ID do release é obrigatório")
    public Long releaseId;

    public FeatureDTO() {}

    public FeatureDTO(Feature feature) {
        this.id = feature.id;
        this.title = feature.title;
        this.description = feature.description;
        this.releaseId = feature.release.id;
    }
}
