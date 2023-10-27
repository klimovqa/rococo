package guru.qa.rococo.service;

import guru.qa.rococo.data.CountryEntity;
import guru.qa.rococo.data.repository.GeoRepository;
import guru.qa.rococo.ex.CountryNotFoundException;
import guru.qa.rococo.model.CountryJson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GeoServiceTest {

    private GeoService testedObject;

    private final UUID countryUUID = UUID.randomUUID();
    private Optional<CountryEntity> countryEntityOptional;
    private Optional<CountryEntity> countryEntityOptionalNotExist;
    private CountryEntity countryEntity;
    private CountryJson countryJson;
    private final String countryName = "Куба";
    private final String countryNameNotExist = "ТакойСтраныНеСуществует";


    @BeforeEach
    void init() {
        countryEntity = new CountryEntity();
        countryEntity.setId(countryUUID);
        countryEntity.setName(countryName);
        countryEntityOptional = Optional.of(countryEntity);
        countryEntityOptionalNotExist = Optional.empty();

        countryJson = new CountryJson();
        countryJson.setId(countryUUID);
        countryJson.setName(countryName);
    }

    @Test
    void getCountryByNameReturnCorrectResult(@Mock GeoRepository repository) {
        when(repository.findByName(eq(countryName)))
                .thenReturn(countryEntityOptional);

        testedObject = new GeoService(repository);

        CountryEntity actual = testedObject.findByName(countryName);

        assertEquals(countryEntity, actual,
                "The essence of the country is not equal to the expected");
    }

    @Test
    void getCountryByNameShouldThrowCountryNotFoundExceptionIfCountryNotFound(
            @Mock GeoRepository repository) {
        when(repository.findByName(eq(countryNameNotExist)))
                .thenReturn(countryEntityOptionalNotExist);

        testedObject = new GeoService(repository);

        final CountryNotFoundException exception = assertThrows(CountryNotFoundException.class,
                () -> testedObject.findByName(countryNameNotExist));
        assertEquals(
                "Country not found.",
                exception.getMessage()
        );
    }




}