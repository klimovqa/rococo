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

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class PaintingService {

    private final PaintingRepository repository;

    @Autowired
    public PaintingService(PaintingRepository repository) {
        this.repository = repository;
    }


    public Page<PaintingEntity> getPainting(Pageable pageable) {
        return repository.findAll(pageable);
    }


    public Page<PaintingEntity> getPaintingByAuthor(String id, Pageable pageable) {
        return repository.findByArtistId(UUID.fromString(id), pageable);
    }

    public PaintingEntity getPaintingById(String id) {
        Optional<PaintingEntity> entity = repository.findById(UUID.fromString(id));
        if (entity.isEmpty()) {
            throw new PaintingNotFoundException("Painting not found.");
        }
        return entity.get();
    }

    public PaintingEntity updatePainting(PaintingJson painting) {
        Optional<PaintingEntity> entity = repository.findById(painting.getId());
        if (entity.isEmpty()) {
            throw new PaintingNotFoundException("Painting not found.");
        }
        return repository.save(entity.get().fromJson(painting));
    }

    public PaintingEntity addPainting(PaintingJson painting) {
        PaintingEntity entity = new PaintingEntity();
        return repository.save(entity.fromJson(painting));
    }

    public Page<PaintingEntity> getPaintingByTitle(Pageable pageable, String title) {
        return repository.findByTitleContainsIgnoreCase(title, pageable);
    }

}