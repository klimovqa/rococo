package guru.qa.rococo.service;

import guru.qa.rococo.data.ArtistEntity;
import guru.qa.rococo.data.repository.ArtistRepository;
import guru.qa.rococo.ex.ArtistNotFoundException;
import guru.qa.rococo.model.ArtistJson;
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

@ExtendWith(MockitoExtension.class)
class ArtistServiceTest {


    private ArtistService testedObject;
    private final UUID uuid = UUID.randomUUID();;
    private final UUID uuidNotExist = UUID.randomUUID();;
    private final String testName = "test";;
    private final String bio = "bio";;
    private final String photo = "photo";;
    private ArtistEntity artistEntity;

    @BeforeEach
    void init(){
        artistEntity = new ArtistEntity();
        artistEntity.setId(uuid);
        artistEntity.setName(testName);
        artistEntity.setBiography(testName);
        artistEntity.setBiography(bio);
        artistEntity.setPhoto(photo);
    }

    @Test
    void findByIdCorrectArtist(@Mock ArtistRepository repository) {
        when(repository.findById(eq(uuid)))
                .thenReturn(Optional.of(artistEntity));

        testedObject = new ArtistService(repository);

        ArtistEntity entity = testedObject.findById(uuid);
        assertEquals(entity.getId(), uuid);
        assertEquals(entity.getName(), testName);
        assertEquals(entity.getBiography(), bio);
        assertEquals(entity.getPhoto(), photo);
    }
    @Test
    void findByIdThrowExceptionIfNotFoundUuid(@Mock ArtistRepository repository) {
        when(repository.findById(eq(uuidNotExist)))
                .thenReturn(Optional.empty());

        testedObject = new ArtistService(repository);

        final ArtistNotFoundException exception = assertThrows(ArtistNotFoundException.class,
                () -> testedObject.findById(uuidNotExist));
        assertEquals(
                "Artist with id - " + uuidNotExist + " not found.",
                exception.getMessage()
        );
    }

    @Test
    void requiredArtisShouldUpdated(@Mock ArtistRepository repository) {
        when(repository.findById(eq(uuid)))
                .thenReturn(Optional.of(artistEntity));

        when(repository.save(any(ArtistEntity.class)))
                .thenAnswer(answer -> answer.getArguments()[0]);

        testedObject = new ArtistService(repository);

        final ArtistJson toBeUpdated = new ArtistJson();
        toBeUpdated.setId(uuid);
        toBeUpdated.setName("newName");
        toBeUpdated.setBiography("newBio");
        toBeUpdated.setPhoto("newPhoto");
        final ArtistEntity result = testedObject.update(toBeUpdated);
        assertEquals(uuid, result.getId());
        assertEquals("newName", result.getName());
        assertEquals("newBio", result.getBiography());
        assertEquals("newPhoto", result.getPhoto());

        verify(repository, times(1)).save(any(ArtistEntity.class));
    }

    @Test
    void requiredArtisShouldAdded(@Mock ArtistRepository repository) {
        when(repository.save(any(ArtistEntity.class)))
                .thenReturn(artistEntity);
                //.thenAnswer(answer -> answer.getArguments()[0]);

        testedObject = new ArtistService(repository);

        final ArtistJson toBeAdded = new ArtistJson();
        toBeAdded.setName("test");
        toBeAdded.setBiography("bio");
        toBeAdded.setPhoto("photo");
        final ArtistEntity result = testedObject.add(toBeAdded);
        assertEquals(uuid, result.getId());
        assertEquals("test", result.getName());
        assertEquals("bio", result.getBiography());
        assertEquals("photo", result.getPhoto());

        verify(repository, times(1)).save(any(ArtistEntity.class));
    }
}