package guru.qa.rococo.rest;

import guru.qa.rococo.api.GatewayClient;
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
@Story("api/museum")
@DisplayName("[REST] api/museum")
@Tag("REST")
public class GatewayRestTest extends BaseRestTest {

    final GatewayClient gatewayClient = new GatewayClient();
    final MuseumRepository museumRepository = new MuseumRepositoryImp();


    @DisplayName("При запросе api/museum?size=4&page=0 возвращаются первые четыре музея")
    @Test
    void checkThatFirstFourMuseumsReturningTest() throws IOException {
        Page<MuseumJson> museum = gatewayClient.getMuseum(4, 0);
        List<MuseumEntity> entities = museumRepository.findByMuseum(4, 0);
        List<MuseumJson> rest = museum.get().collect(Collectors.toList());
        List<MuseumJson> db = entities.stream()
                .map(MuseumEntity::toJson).collect(Collectors.toList());

        step("Проверить, что ответ api/museum содержит 4 музея", () -> Assertions
                .assertThat(rest)
                .containsExactlyInAnyOrderElementsOf(db));
    }

}
