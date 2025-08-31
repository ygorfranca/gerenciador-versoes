package com.exemplo.gerenciadorversoes.dto;

import com.exemplo.gerenciadorversoes.model.Release;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ReleaseDTO {

    public Long id;

    @NotBlank(message = "Título é obrigatório")
    @Size(max = 200, message = "Título deve ter no máximo 200 caracteres")
    public String title;

    @Size(max = 1000, message = "Descrição deve ter no máximo 1000 caracteres")
    public String description;

    public LocalDateTime releasedAt;

    @NotNull(message = "ID da versão é obrigatório")
    public Long versionId;

    public List<FeatureDTO> features;
    public List<BugFixDTO> bugFixes;

    public ReleaseDTO() {}

    public ReleaseDTO(Release release) {
        this.id = release.id;
        this.title = release.title;
        this.description = release.description;
        this.releasedAt = release.releasedAt;
        this.versionId = release.version.id;
        
        if (release.features != null) {
            this.features = release.features.stream()
                .map(FeatureDTO::new)
                .collect(Collectors.toList());
        }
        
        if (release.bugFixes != null) {
            this.bugFixes = release.bugFixes.stream()
                .map(BugFixDTO::new)
                .collect(Collectors.toList());
        }
    }
}
