package guru.qa.rococo.rest;

import guru.qa.rococo.BaseTest;
import guru.qa.rococo.api.ArtistClient;
import guru.qa.rococo.api.model.ArtistJson;
import guru.qa.rococo.db.entity.artist.ArtistEntity;
import guru.qa.rococo.db.repository.ArtistRepository;
import guru.qa.rococo.db.repository.imp.ArtistRepositoryImp;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static io.qameta.allure.Allure.step;

@Epic("[REST]")
@DisplayName("[REST] API/ARTIST")
@Story("api/artist")
@Tag("REST")
public class ArtistRestTest extends BaseTest {

    final ArtistClient artistClient = new ArtistClient();
    final ArtistRepository artistRepository = new ArtistRepositoryImp();


    @DisplayName("При запросе api/artist?size=18&page=0 возвращаются первые 18 художников")
    @Test
    void checkThatFirstFourArtistReturningTest() throws IOException {
        Page<ArtistJson> artist = artistClient.getArtist(18, 0);
        List<ArtistEntity> entities = artistRepository.findByArtist(18, 0);
        List<ArtistJson> rest = artist.get().collect(Collectors.toList());
        List<ArtistJson> db = entities.stream()
                .map(ArtistJson::fromEntity).collect(Collectors.toList());

        step("Проверить, что ответ api/artist содержит ответ", () -> Assertions
                .assertThat(rest)
                .containsExactlyInAnyOrderElementsOf(db));
    }

    @DisplayName("При запросе api/artist/uuid возвращается информация о художнике")
    @Test
    void checkReturnArtistInfoTest() throws IOException {
        final String ARTIST = "Клод Моне";
        ArtistEntity artistDb = artistRepository.findByNameArtist(ARTIST);
        ArtistJson artistInfo = artistClient.getArtistInfo(artistDb.getId());

        step("Проверить, что ответ api/artist/" + artistDb.getId() + " содержит информацию о " + ARTIST, () -> Assertions
                .assertThat(artistInfo)
                .isEqualTo(ArtistJson.fromEntity(artistDb)));
    }
}
