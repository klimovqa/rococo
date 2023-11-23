package guru.qa.rococo.rest;

import guru.qa.rococo.BaseTest;
import guru.qa.rococo.api.MuseumClient;
import guru.qa.rococo.api.model.MuseumJson;
import guru.qa.rococo.db.entity.museum.MuseumEntity;
import guru.qa.rococo.db.repository.MuseumRepository;
import guru.qa.rococo.db.repository.imp.MuseumRepositoryImp;
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
@DisplayName("[REST] API/MUSEUM")
@Story("api/museum")
@Tag("REST")
public class MuseumRestTest extends BaseTest {

    final MuseumClient museumClient = new MuseumClient();
    final MuseumRepository museumRepository = new MuseumRepositoryImp();


    @DisplayName("При запросе api/museum?size=4&page=0 возвращаются первые четыре музея")
    @Test
    void checkThatFirstFourMuseumsReturningTest() throws IOException {
        Page<MuseumJson> museum = museumClient.getMuseum(4, 0);
        List<MuseumEntity> entities = museumRepository.findByMuseum(4, 0);
        List<MuseumJson> rest = museum.get().collect(Collectors.toList());
        List<MuseumJson> db = entities.stream()
                .map(MuseumEntity::toJson).collect(Collectors.toList());

        step("Проверить, что ответ api/museum содержит 4 музея", () -> Assertions
                .assertThat(rest)
                .containsExactlyInAnyOrderElementsOf(db));
    }

    @DisplayName("При запросе api/museum/uuid возвращается информация о музеи")
    @Test
    void checkReturnMuseumInfoTest() throws IOException {
        final String MUSEUM = "Лувр";
        MuseumEntity museumDb = museumRepository.findByNameMuseum(MUSEUM);
        MuseumJson museumInfo = museumClient.getMuseumInfo(museumDb.getId());

        step("Проверить, что ответ api/museum/" + museumDb.getId() + " содержит информацию о " + MUSEUM, () -> Assertions
                .assertThat(museumInfo)
                .isEqualTo(museumDb.toJson()));
    }

}
