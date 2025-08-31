package com.exemplo.gerenciadorversoes.service;

import com.exemplo.gerenciadorversoes.dto.ReleaseDTO;
import com.exemplo.gerenciadorversoes.model.Release;
import com.exemplo.gerenciadorversoes.model.Version;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ReleaseService {

    @Transactional
    public ReleaseDTO create(ReleaseDTO releaseDTO) {
        Version version = Version.findById(releaseDTO.versionId);
        if (version == null) {
            throw new NotFoundException("Versão não encontrada com ID: " + releaseDTO.versionId);
        }

        // Verificar se já existe release para esta versão
        Release existingRelease = Release.findByVersion(version);
        if (existingRelease != null) {
            throw new IllegalArgumentException("Já existe um release para esta versão");
        }

        Release release = new Release();
        release.title = releaseDTO.title;
        release.description = releaseDTO.description;
        release.version = version;
        release.persist();

        return new ReleaseDTO(release);
    }

    public ReleaseDTO findById(Long id) {
        Release release = Release.findById(id);
        if (release == null) {
            throw new NotFoundException("Release não encontrado com ID: " + id);
        }
        return new ReleaseDTO(release);
    }

    public ReleaseDTO findByVersion(Long versionId) {
        Version version = Version.findById(versionId);
        if (version == null) {
            throw new NotFoundException("Versão não encontrada com ID: " + versionId);
        }

        Release release = Release.findByVersion(version);
        if (release == null) {
            throw new NotFoundException("Release não encontrado para a versão: " + versionId);
        }
        return new ReleaseDTO(release);
    }

    public List<ReleaseDTO> findAll() {
        return Release.listAll().stream()
            .map(release -> new ReleaseDTO((Release) release))
            .collect(Collectors.toList());
    }

    public List<ReleaseDTO> findRecent(int limit) {
        return Release.findRecentReleases(limit).stream()
            .map(ReleaseDTO::new)
            .collect(Collectors.toList());
    }

    @Transactional
    public ReleaseDTO update(Long id, ReleaseDTO releaseDTO) {
        Release release = Release.findById(id);
        if (release == null) {
            throw new NotFoundException("Release não encontrado com ID: " + id);
        }

        Version version = Version.findById(releaseDTO.versionId);
        if (version == null) {
            throw new NotFoundException("Versão não encontrada com ID: " + releaseDTO.versionId);
        }

        // Verificar se já existe outro release para esta versão
        Release existingRelease = Release.findByVersion(version);
        if (existingRelease != null && !existingRelease.id.equals(id)) {
            throw new IllegalArgumentException("Já existe um release para esta versão");
        }

        release.title = releaseDTO.title;
        release.description = releaseDTO.description;
        release.version = version;

        return new ReleaseDTO(release);
    }

    @Transactional
    public void delete(Long id) {
        Release release = Release.findById(id);
        if (release == null) {
            throw new NotFoundException("Release não encontrado com ID: " + id);
        }
        release.delete();
    }
}
