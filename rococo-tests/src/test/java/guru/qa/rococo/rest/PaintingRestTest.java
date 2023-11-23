package guru.qa.rococo.rest;

import guru.qa.rococo.BaseTest;
import guru.qa.rococo.api.PaintingClient;
import guru.qa.rococo.api.model.ArtistJson;
import guru.qa.rococo.api.model.PaintingJson;
import guru.qa.rococo.db.entity.artist.ArtistEntity;
import guru.qa.rococo.db.entity.museum.MuseumEntity;
import guru.qa.rococo.db.entity.painting.PaintingEntity;
import guru.qa.rococo.db.repository.ArtistRepository;
import guru.qa.rococo.db.repository.MuseumRepository;
import guru.qa.rococo.db.repository.PaintingRepository;
import guru.qa.rococo.db.repository.imp.ArtistRepositoryImp;
import guru.qa.rococo.db.repository.imp.MuseumRepositoryImp;
import guru.qa.rococo.db.repository.imp.PaintingRepositoryImp;
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
@DisplayName("[REST] API/PAINTING")
@Story("api/painting")
@Tag("REST")
public class PaintingRestTest extends BaseTest {

    final PaintingClient paintingClient = new PaintingClient();
    final MuseumRepository museumRepository = new MuseumRepositoryImp();
    final ArtistRepository artistRepository = new ArtistRepositoryImp();
    final PaintingRepository paintingRepository = new PaintingRepositoryImp();


    @DisplayName("При запросе api/painting?size=18&page=0 возвращаются первые 18 художников")
    @Test
    void checkThatFirstFourPaintingReturningTest() throws IOException {
        Page<PaintingJson> painting = paintingClient.getPainting(9, 0);
        List<PaintingEntity> entities = paintingRepository.findByPainting(9, 0);

        List<PaintingJson> db = entities.stream().map(paintingDb -> {
            MuseumEntity museumDb = museumRepository.findById(paintingDb.getMuseumId());
            ArtistEntity artistDb = artistRepository.findById(paintingDb.getArtistId());

            PaintingJson paintingJson = PaintingJson.fromEntity(paintingDb);
            paintingJson.setArtist(ArtistJson.fromEntity(artistDb));
            paintingJson.setMuseum(museumDb.toJson());
            return paintingJson;
        }).collect(Collectors.toList());

        List<PaintingJson> rest = painting.get().collect(Collectors.toList());


        step("Проверить, что ответ api/painting содержит ответ", () -> Assertions
                .assertThat(rest)
                .containsExactlyInAnyOrderElementsOf(db));
    }

    @DisplayName("При запросе api/painting/uuid возвращается информация о картине")
    @Test
    void checkReturnPaintingInfoTest() throws IOException {
        final String PAINTING = "Большой рейд в Кронштадте";
        PaintingEntity paintingDb = paintingRepository.findByNamePainting(PAINTING);
        MuseumEntity museumDb = museumRepository.findById(paintingDb.getMuseumId());
        ArtistEntity artistDb = artistRepository.findById(paintingDb.getArtistId());
        PaintingJson paintingInfo = paintingClient.getPaintingInfo(paintingDb.getId());

        PaintingJson paintingJson = PaintingJson.fromEntity(paintingDb);
        paintingJson.setArtist(ArtistJson.fromEntity(artistDb));
        paintingJson.setMuseum(museumDb.toJson());

        step("Проверить, что ответ api/painting/" + paintingDb.getId() + " содержит информацию о " + PAINTING, () -> Assertions
                .assertThat(paintingInfo)
                .isEqualTo(paintingJson));
    }
}
