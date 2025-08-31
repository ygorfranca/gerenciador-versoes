package com.exemplo.gerenciadorversoes.service;

import com.exemplo.gerenciadorversoes.dto.BugFixDTO;
import com.exemplo.gerenciadorversoes.model.BugFix;
import com.exemplo.gerenciadorversoes.model.Release;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class BugFixService {

    @Transactional
    public BugFixDTO create(BugFixDTO bugFixDTO) {
        Release release = Release.findById(bugFixDTO.releaseId);
        if (release == null) {
            throw new NotFoundException("Release não encontrado com ID: " + bugFixDTO.releaseId);
        }

        BugFix bugFix = new BugFix();
        bugFix.title = bugFixDTO.title;
        bugFix.description = bugFixDTO.description;
        bugFix.release = release;
        bugFix.persist();

        return new BugFixDTO(bugFix);
    }

    public BugFixDTO findById(Long id) {
        BugFix bugFix = BugFix.findById(id);
        if (bugFix == null) {
            throw new NotFoundException("Bug fix não encontrado com ID: " + id);
        }
        return new BugFixDTO(bugFix);
    }

    public List<BugFixDTO> findByRelease(Long releaseId) {
        Release release = Release.findById(releaseId);
        if (release == null) {
            throw new NotFoundException("Release não encontrado com ID: " + releaseId);
        }

        return BugFix.findByRelease(release).stream()
            .map(BugFixDTO::new)
            .collect(Collectors.toList());
    }

    public List<BugFixDTO> findByTitle(String title) {
        return BugFix.findByTitleContaining(title).stream()
            .map(BugFixDTO::new)
            .collect(Collectors.toList());
    }

    public List<BugFixDTO> findAll() {
        return BugFix.listAll().stream()
            .map(bugFix -> new BugFixDTO((BugFix) bugFix))
            .collect(Collectors.toList());
    }

    @Transactional
    public BugFixDTO update(Long id, BugFixDTO bugFixDTO) {
        BugFix bugFix = BugFix.findById(id);
        if (bugFix == null) {
            throw new NotFoundException("Bug fix não encontrado com ID: " + id);
        }

        Release release = Release.findById(bugFixDTO.releaseId);
        if (release == null) {
            throw new NotFoundException("Release não encontrado com ID: " + bugFixDTO.releaseId);
        }

        bugFix.title = bugFixDTO.title;
        bugFix.description = bugFixDTO.description;
        bugFix.release = release;

        return new BugFixDTO(bugFix);
    }

    @Transactional
    public void delete(Long id) {
        BugFix bugFix = BugFix.findById(id);
        if (bugFix == null) {
            throw new NotFoundException("Bug fix não encontrado com ID: " + id);
        }
        bugFix.delete();
    }
}
