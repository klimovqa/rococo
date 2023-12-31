package guru.qa.rococo.service;

import guru.qa.rococo.data.PaintingEntity;
import guru.qa.rococo.data.repository.PaintingRepository;
import guru.qa.rococo.ex.PaintingNotFoundException;
import guru.qa.rococo.model.PaintingJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Component
public class PaintingService {

    private final PaintingRepository repository;

    @Autowired
    public PaintingService(PaintingRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Page<PaintingEntity> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }


    @Transactional(readOnly = true)
    public Page<PaintingEntity> findByAuthor(UUID uuid, Pageable pageable) {
        return repository.findByArtistId(uuid, pageable);
    }

    @Transactional(readOnly = true)
    public PaintingEntity findById(UUID uuid) {
        return repository.findById(uuid)
                .orElseThrow(() ->
                        new PaintingNotFoundException("Painting with uuid " + uuid + " not found.")
                );
    }

    @Transactional(readOnly = true)
    public Page<PaintingEntity> findByTitle(Pageable pageable, String title) {
        return repository.findByTitleContainsIgnoreCase(title, pageable);
    }

    @Transactional
    public PaintingEntity update(PaintingJson painting) {
        PaintingEntity entity = findById(painting.getId());
        return repository.save(entity.fromJson(painting));
    }

    @Transactional
    public PaintingEntity add(PaintingJson painting) {
        return repository.save(new PaintingEntity().fromJson(painting));
    }

}