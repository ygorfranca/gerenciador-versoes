package com.exemplo.gerenciadorversoes.service;

import com.exemplo.gerenciadorversoes.dto.VersionDTO;
import com.exemplo.gerenciadorversoes.exception.BusinessRuleException;
import com.exemplo.gerenciadorversoes.exception.ResourceNotFoundException;
import com.exemplo.gerenciadorversoes.model.Project;
import com.exemplo.gerenciadorversoes.model.Version;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class VersionService {

    @Transactional
    public VersionDTO create(VersionDTO versionDTO) {
        Project project = Project.findById(versionDTO.projectId);
        if (project == null) {
            throw new ResourceNotFoundException("Projeto", "ID", versionDTO.projectId);
        }

        validateUniqueVersion(project, versionDTO.major, versionDTO.minor, versionDTO.patch, null);

        Version version = new Version();
        version.major = versionDTO.major;
        version.minor = versionDTO.minor;
        version.patch = versionDTO.patch;
        version.status = versionDTO.status;
        version.project = project;
        version.persist();

        return new VersionDTO(version);
    }

    public VersionDTO findById(Long id) {
        Version version = Version.findById(id);
        if (version == null) {
            throw new ResourceNotFoundException("Versão", "ID", id);
        }
        return new VersionDTO(version);
    }

    public List<VersionDTO> findByProject(Long projectId) {
        Project project = Project.findById(projectId);
        if (project == null) {
            throw new ResourceNotFoundException("Projeto", "ID", projectId);
        }

        return Version.findByProject(project).stream()
            .map(VersionDTO::new)
            .collect(Collectors.toList());
    }

    public List<VersionDTO> findByStatus(String status) {
        return Version.findByStatus(status).stream()
            .map(VersionDTO::new)
            .collect(Collectors.toList());
    }

    public List<VersionDTO> findAll() {
        return Version.listAll().stream()
            .map(version -> new VersionDTO((Version) version))
            .collect(Collectors.toList());
    }

    @Transactional
    public VersionDTO update(Long id, VersionDTO versionDTO) {
        Version version = Version.findById(id);
        if (version == null) {
            throw new ResourceNotFoundException("Versão", "ID", id);
        }

        Project project = Project.findById(versionDTO.projectId);
        if (project == null) {
            throw new ResourceNotFoundException("Projeto", "ID", versionDTO.projectId);
        }

        validateUniqueVersion(project, versionDTO.major, versionDTO.minor, versionDTO.patch, id);

        version.major = versionDTO.major;
        version.minor = versionDTO.minor;
        version.patch = versionDTO.patch;
        version.status = versionDTO.status;
        version.project = project;

        return new VersionDTO(version);
    }

    @Transactional
    public void delete(Long id) {
        Version version = Version.findById(id);
        if (version == null) {
            throw new ResourceNotFoundException("Versão", "ID", id);
        }
        version.delete();
    }

    private void validateUniqueVersion(Project project, Integer major, Integer minor, Integer patch, Long excludeId) {
        Version existingVersion = Version.findByProjectAndVersion(project, major, minor, patch);
        if (existingVersion != null && (excludeId == null || !existingVersion.id.equals(excludeId))) {
            throw new BusinessRuleException("Versão já existe para este projeto: " + major + "." + minor + "." + patch);
        }
    }
}
