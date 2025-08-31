package com.exemplo.gerenciadorversoes.dto;

import com.exemplo.gerenciadorversoes.model.Version;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class VersionDTO {

    public Long id;

    @NotNull(message = "Major é obrigatório")
    @Min(value = 0, message = "Major deve ser maior ou igual a 0")
    public Integer major;

    @NotNull(message = "Minor é obrigatório")
    @Min(value = 0, message = "Minor deve ser maior ou igual a 0")
    public Integer minor;

    @NotNull(message = "Patch é obrigatório")
    @Min(value = 0, message = "Patch deve ser maior ou igual a 0")
    public Integer patch;

    @NotNull(message = "Status é obrigatório")
    @Pattern(regexp = "draft|published|deprecated", message = "Status deve ser: draft, published ou deprecated")
    public String status;

    @NotNull(message = "ID do projeto é obrigatório")
    public Long projectId;

    public String versionString;

    public VersionDTO() {}

    public VersionDTO(Version version) {
        this.id = version.id;
        this.major = version.major;
        this.minor = version.minor;
        this.patch = version.patch;
        this.status = version.status;
        this.projectId = version.project.id;
        this.versionString = version.getVersionString();
    }
}
