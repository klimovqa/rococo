package guru.qa.rococo.service;

import guru.qa.rococo.data.ArtistEntity;
import guru.qa.rococo.data.repository.ArtistRepository;
import guru.qa.rococo.ex.ArtistNotFoundException;
import guru.qa.rococo.model.ArtistJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static java.lang.String.format;

@Slf4j
@Component
public class ArtistService {

    private final ArtistRepository repository;

    @Autowired
    public ArtistService(ArtistRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Page<ArtistEntity> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public ArtistEntity findById(String id) {
        return repository.findById(UUID.fromString(id))
                .orElseThrow(() ->
                        new ArtistNotFoundException("Artist with id - " + id + " not found."));
    }


    @Transactional(readOnly = true)
    public Page<ArtistEntity> findByName(Pageable pageable, String name) {
        return repository.findByNameContainsIgnoreCase(name, pageable);
    }


    @Transactional
    public ArtistEntity update(ArtistJson artist) {
        ArtistEntity entity = repository.findById(artist.getId())
                .orElseThrow(() ->
                        new ArtistNotFoundException(format("Artist with id - %s not found.", artist.getId())));
        return repository.save(entity.fromJson(artist));
    }

    @Transactional
    public ArtistEntity add(ArtistJson artist) {
        return repository.save(new ArtistEntity().fromJson(artist));
    }
}