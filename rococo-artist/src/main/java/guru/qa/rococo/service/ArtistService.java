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

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class ArtistService {

    private final ArtistRepository repository;

    @Autowired
    public ArtistService(ArtistRepository repository) {
        this.repository = repository;
    }


    public Page<ArtistEntity> getArtist(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public ArtistEntity getArtistById(String id) {
        Optional<ArtistEntity> entity = repository.findById(UUID.fromString(id));
        if (entity.isEmpty()) {
            throw new ArtistNotFoundException("Artist not found.");
        }
        return entity.get();
    }

    public ArtistEntity updateArtist(ArtistJson artist) {
        Optional<ArtistEntity> entity = repository.findById(artist.getId());
        if (entity.isEmpty()) {
            throw new ArtistNotFoundException("Artist not found.");
        }
        return repository.save(entity.get().fromJson(artist));
    }

    public ArtistEntity addArtist(ArtistJson artist) {
        ArtistEntity entity = new ArtistEntity();
        return repository.save(entity.fromJson(artist));
    }

    public Page<ArtistEntity> getArtistByName(Pageable pageable, String name) {
        return repository.findByNameContainsIgnoreCase(name, pageable);
    }
}