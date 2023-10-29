package guru.qa.rococo.service;

import guru.qa.rococo.data.PaintingEntity;
import guru.qa.rococo.data.repository.PaintingRepository;
import guru.qa.rococo.ex.PaintingNotFoundException;
import guru.qa.rococo.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class PaintingServiceTest {
    private PaintingService testedObject;
    private final UUID uuid = UUID.randomUUID();
    ;
    private final UUID uuidArtist = UUID.randomUUID();
    ;
    private final UUID uuidMuseum = UUID.randomUUID();
    ;
    private final UUID uuidNotExist = UUID.randomUUID();
    ;
    private final String title = "title";
    ;
    private final String desc = "desc";
    ;
    private final String content = "content";
    private PaintingEntity paintingEntity;

    @BeforeEach
    void init() {
        paintingEntity = new PaintingEntity();
        paintingEntity.setId(uuid);
        paintingEntity.setTitle(title);
        paintingEntity.setDescription(desc);
        paintingEntity.setContent(content);
        paintingEntity.setArtistId(uuidArtist);
        paintingEntity.setMuseumId(uuidMuseum);
    }

    @Test
    void findByIdCorrectPainting(@Mock PaintingRepository repository) {
        when(repository.findById(eq(uuid)))
                .thenReturn(Optional.of(paintingEntity));

        testedObject = new PaintingService(repository);

        PaintingEntity entity = testedObject.findById(uuid);
        assertEquals(entity.getId(), uuid);
        assertEquals(entity.getTitle(), title);
        assertEquals(entity.getDescription(), desc);
        assertEquals(entity.getContent(), content);
        assertEquals(entity.getArtistId(), uuidArtist);
        assertEquals(entity.getMuseumId(), uuidMuseum);
    }

    @Test
    void findByIdThrowExceptionIfNotFoundUuid(@Mock PaintingRepository repository) {
        when(repository.findById(eq(uuidNotExist)))
                .thenReturn(Optional.empty());

        testedObject = new PaintingService(repository);

        final PaintingNotFoundException exception = assertThrows(PaintingNotFoundException.class,
                () -> testedObject.findById(uuidNotExist));
        assertEquals(
                "Painting with uuid " + uuidNotExist + " not found.",
                exception.getMessage()
        );
    }

    @Test
    void requiredPaintingShouldUpdated(@Mock PaintingRepository repository) {
        when(repository.findById(eq(uuid)))
                .thenReturn(Optional.of(paintingEntity));

        when(repository.save(any(PaintingEntity.class)))
                .thenAnswer(answer -> answer.getArguments()[0]);

        testedObject = new PaintingService(repository);

        final PaintingJson toBeUpdated = new PaintingJson();
        toBeUpdated.setId(uuid);
        toBeUpdated.setTitle("newTitle");
        toBeUpdated.setDescription("newDesc");
        toBeUpdated.setContent("newContent");
        ArtistJson artistJson = new ArtistJson();
        artistJson.setId(uuidArtist);
        toBeUpdated.setArtist(artistJson);
        MuseumJson museumJson = new MuseumJson();
        museumJson.setId(uuidMuseum);
        toBeUpdated.setMuseum(museumJson);

        final PaintingEntity result = testedObject.update(toBeUpdated);
        assertEquals(uuid, result.getId());
        assertEquals("newTitle", result.getTitle());
        assertEquals("newDesc", result.getDescription());
        assertEquals("newContent", result.getContent());
        assertEquals(uuidArtist, result.getArtistId());
        assertEquals(uuidMuseum, result.getMuseumId());

        verify(repository, times(1)).save(any(PaintingEntity.class));
    }

    @Test
    void requiredPaintingShouldAdded(@Mock PaintingRepository repository) {
        when(repository.save(any(PaintingEntity.class)))
                .thenReturn(paintingEntity);

        testedObject = new PaintingService(repository);

        final PaintingJson toBeAdded = new PaintingJson();
        toBeAdded.setTitle("title");
        toBeAdded.setDescription("desc");
        toBeAdded.setContent("content");
        ArtistJson artistJson = new ArtistJson();
        artistJson.setId(uuidArtist);
        toBeAdded.setArtist(artistJson);
        MuseumJson museumJson = new MuseumJson();
        museumJson.setId(uuidMuseum);
        toBeAdded.setMuseum(museumJson);

        final PaintingEntity result = testedObject.add(toBeAdded);
        assertEquals(uuid, result.getId());
        assertEquals("title", result.getTitle());
        assertEquals("desc", result.getDescription());
        assertEquals("content", result.getContent());

        verify(repository, times(1)).save(any(PaintingEntity.class));
    }
}