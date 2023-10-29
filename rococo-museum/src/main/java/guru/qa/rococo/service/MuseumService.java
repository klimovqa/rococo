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
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Component
public class MuseumService {

    private final MuseumRepository repository;

    @Autowired
    public MuseumService(MuseumRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Page<MuseumEntity> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public MuseumEntity findById(UUID uuid) {
        return repository.findById(uuid).
                orElseThrow(() ->
                        new MuseumNotFoundException("Museum with uuid " + uuid + " not found."));
    }


    @Transactional(readOnly = true)
    public Page<MuseumEntity> findByTitle(Pageable pageable, String title) {
        return repository.findByTitleContainsIgnoreCase(title, pageable);
    }

    @Transactional
    public MuseumEntity update(MuseumJson museum) {
        MuseumEntity entity = findById(museum.getId());
        return repository.save(entity.fromJson(museum));
    }

    @Transactional
    public MuseumEntity add(MuseumJson museum) {
        return repository.save(new MuseumEntity().fromJson(museum));
    }
}