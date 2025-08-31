package com.exemplo.gerenciadorversoes.dto;

import com.exemplo.gerenciadorversoes.model.BugFix;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class BugFixDTO {

    public Long id;

    @NotBlank(message = "Título é obrigatório")
    @Size(max = 200, message = "Título deve ter no máximo 200 caracteres")
    public String title;

    @Size(max = 1000, message = "Descrição deve ter no máximo 1000 caracteres")
    public String description;

    @NotNull(message = "ID do release é obrigatório")
    public Long releaseId;

    public BugFixDTO() {}

    public BugFixDTO(BugFix bugFix) {
        this.id = bugFix.id;
        this.title = bugFix.title;
        this.description = bugFix.description;
        this.releaseId = bugFix.release.id;
    }
}
