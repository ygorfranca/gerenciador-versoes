package com.exemplo.gerenciadorversoes.service;

import com.exemplo.gerenciadorversoes.dto.FeatureDTO;
import com.exemplo.gerenciadorversoes.exception.ResourceNotFoundException;
import com.exemplo.gerenciadorversoes.model.Feature;
import com.exemplo.gerenciadorversoes.model.Release;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class FeatureService {

    @Transactional
    public FeatureDTO create(FeatureDTO featureDTO) {
        Release release = Release.findById(featureDTO.releaseId);
        if (release == null) {
            throw new ResourceNotFoundException("Release não encontrado com ID: " + featureDTO.releaseId);
        }

        Feature feature = new Feature();
        feature.title = featureDTO.title;
        feature.description = featureDTO.description;
        feature.release = release;
        feature.persist();

        return new FeatureDTO(feature);
    }

    public FeatureDTO findById(Long id) {
        Feature feature = Feature.findById(id);
        if (feature == null) {
            throw new ResourceNotFoundException("Feature não encontrada com ID: " + id);
        }
        return new FeatureDTO(feature);
    }

    public List<FeatureDTO> findByRelease(Long releaseId) {
        Release release = Release.findById(releaseId);
        if (release == null) {
            throw new ResourceNotFoundException("Release não encontrado com ID: " + releaseId);
        }

        return Feature.findByRelease(release).stream()
            .map(FeatureDTO::new)
            .collect(Collectors.toList());
    }

    public List<FeatureDTO> findByTitle(String title) {
        return Feature.findByTitleContaining(title).stream()
            .map(FeatureDTO::new)
            .collect(Collectors.toList());
    }

    public List<FeatureDTO> findAll() {
        return Feature.listAll().stream()
            .map(feature -> new FeatureDTO((Feature) feature))
            .collect(Collectors.toList());
    }

    @Transactional
    public FeatureDTO update(Long id, FeatureDTO featureDTO) {
        Feature feature = Feature.findById(id);
        if (feature == null) {
            throw new ResourceNotFoundException("Feature não encontrada com ID: " + id);
        }

        Release release = Release.findById(featureDTO.releaseId);
        if (release == null) {
            throw new ResourceNotFoundException("Release não encontrado com ID: " + featureDTO.releaseId);
        }

        feature.title = featureDTO.title;
        feature.description = featureDTO.description;
        feature.release = release;

        return new FeatureDTO(feature);
    }

    @Transactional
    public void delete(Long id) {
        Feature feature = Feature.findById(id);
        if (feature == null) {
            throw new ResourceNotFoundException("Feature não encontrada com ID: " + id);
        }
        feature.delete();
    }
}