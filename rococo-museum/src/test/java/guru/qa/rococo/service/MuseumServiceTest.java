package guru.qa.rococo.service;

import guru.qa.rococo.data.LocationEntity;
import guru.qa.rococo.data.MuseumEntity;
import guru.qa.rococo.data.repository.MuseumRepository;
import guru.qa.rococo.ex.MuseumNotFoundException;
import guru.qa.rococo.model.CountryJson;
import guru.qa.rococo.model.GeoJson;
import guru.qa.rococo.model.MuseumJson;
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
class MuseumServiceTest {
    private MuseumService testedObject;
    private final UUID uuid = UUID.randomUUID();
    private final UUID uuidCountry = UUID.randomUUID();
    private final UUID uuidLocation = UUID.randomUUID();
    private final UUID uuidNotExist = UUID.randomUUID();
    private final String title = "title";
    private final String desc = "desc";
    private final String photo = "photo";
    private final String city = "Moscow";
    private final String country = "Russia";
    private MuseumEntity museumEntity;

    @BeforeEach
    void init() {
        LocationEntity location = new LocationEntity();
        location.setCity(city);
        location.setId(uuidLocation);
        location.setCountryName(country);
        location.setCountryId(uuidCountry);
        museumEntity = new MuseumEntity();
        museumEntity.setId(uuid);
        museumEntity.setTitle(title);
        museumEntity.setDescription(desc);
        museumEntity.setLocation(location);
        museumEntity.setPhoto(photo);
    }

    @Test
    void findByIdCorrectMuseum(@Mock MuseumRepository repository) {
        when(repository.findById(eq(uuid)))
                .thenReturn(Optional.of(museumEntity));

        testedObject = new MuseumService(repository);

        MuseumEntity entity = testedObject.findById(uuid);
        assertEquals(entity.getId(), uuid);
        assertEquals(entity.getTitle(), title);
        assertEquals(entity.getDescription(), desc);
        assertEquals(entity.getPhoto(), photo);
        LocationEntity loc = entity.getLocation();
        assertEquals(loc.getCity(), city);
        assertEquals(loc.getId(), uuidLocation);
        assertEquals(loc.getCountryId(), uuidCountry);
        assertEquals(loc.getCountryName(), country);
    }

    @Test
    void findByIdThrowExceptionIfNotFoundUuid(@Mock MuseumRepository repository) {
        when(repository.findById(eq(uuidNotExist)))
                .thenReturn(Optional.empty());

        testedObject = new MuseumService(repository);

        final MuseumNotFoundException exception = assertThrows(MuseumNotFoundException.class,
                () -> testedObject.findById(uuidNotExist));
        assertEquals(
                "Museum with uuid " + uuidNotExist + " not found.",
                exception.getMessage()
        );
    }

    @Test
    void requiredMuseumShouldUpdated(@Mock MuseumRepository repository) {
        when(repository.findById(eq(uuid)))
                .thenReturn(Optional.of(museumEntity));

        when(repository.save(any(MuseumEntity.class)))
                .thenAnswer(answer -> answer.getArguments()[0]);

        testedObject = new MuseumService(repository);

        final MuseumJson toBeUpdated = new MuseumJson();
        toBeUpdated.setId(uuid);
        toBeUpdated.setTitle("newTitle");
        toBeUpdated.setDescription("newDesc");
        toBeUpdated.setPhoto("newPhoto");
        GeoJson geoJson = new GeoJson();
        geoJson.setCity("Moscow");
        CountryJson countryJson = new CountryJson();
        countryJson.setName("Russia");
        countryJson.setId(uuidCountry);
        geoJson.setCountry(countryJson);
        toBeUpdated.setGeo(geoJson);

        final MuseumEntity result = testedObject.update(toBeUpdated);
        assertEquals(uuid, result.getId());
        assertEquals("newTitle", result.getTitle());
        assertEquals("newDesc", result.getDescription());
        assertEquals("newPhoto", result.getPhoto());
        LocationEntity location = result.getLocation();
        assertEquals(city, location.getCity());
        assertEquals(country, location.getCountryName());
        assertEquals(uuidCountry, location.getCountryId());
        assertEquals(uuidLocation, location.getId());

        verify(repository, times(1)).save(any(MuseumEntity.class));
    }

    @Test
    void requiredMuseumShouldAdded(@Mock MuseumRepository repository) {
        when(repository.save(any(MuseumEntity.class)))
                .thenReturn(museumEntity);

        testedObject = new MuseumService(repository);

        final MuseumJson toBeAdded = new MuseumJson();
        toBeAdded.setTitle("title");
        toBeAdded.setDescription("desc");
        toBeAdded.setPhoto("photo");
        GeoJson geoJson = new GeoJson();
        geoJson.setCity("Moscow");
        CountryJson countryJson = new CountryJson();
        countryJson.setName("Russia");
        countryJson.setId(uuidCountry);
        geoJson.setCountry(countryJson);
        toBeAdded.setGeo(geoJson);

        final MuseumEntity result = testedObject.add(toBeAdded);
        assertEquals(uuid, result.getId());
        assertEquals("title", result.getTitle());
        assertEquals("desc", result.getDescription());
        assertEquals("photo", result.getPhoto());
        LocationEntity location = result.getLocation();
        assertEquals(city, location.getCity());
        assertEquals(country, location.getCountryName());
        assertEquals(uuidCountry, location.getCountryId());
        assertEquals(uuidLocation, location.getId());

        verify(repository, times(1)).save(any(MuseumEntity.class));
    }
}