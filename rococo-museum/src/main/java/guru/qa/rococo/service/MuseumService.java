package guru.qa.rococo.service;
import guru.qa.rococo.data.MuseumEntity;
import guru.qa.rococo.data.repository.MuseumRepository;
import guru.qa.rococo.ex.MuseumNotFoundException;
import guru.qa.rococo.model.MuseumJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class MuseumService {

    private final MuseumRepository repository;

    @Autowired
    public MuseumService(MuseumRepository repository) {
        this.repository = repository;
    }


    public Page<MuseumEntity> getMuseums(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public MuseumEntity getMuseumById(String id) {
        Optional<MuseumEntity> entity = repository.findById(UUID.fromString(id));
        if (entity.isEmpty()) {
            throw new MuseumNotFoundException("Museum not found.");
        }
        return entity.get();
    }

    public MuseumEntity updateMuseum(MuseumJson museum) {
        Optional<MuseumEntity> entity = repository.findById(museum.getId());
        if (entity.isEmpty()) {
            throw new MuseumNotFoundException("Museum not found.");
        }
        return repository.save(entity.get().fromJson(museum));
    }

    public MuseumEntity addMuseum(MuseumJson museum) {
        MuseumEntity entity = new MuseumEntity();
        return repository.save(entity.fromJson(museum));
    }
}