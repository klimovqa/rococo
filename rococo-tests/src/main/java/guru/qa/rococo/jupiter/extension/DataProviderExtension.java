package guru.qa.rococo.jupiter.extension;

import guru.qa.rococo.api.model.ArtistJson;
import guru.qa.rococo.api.model.MuseumJson;
import guru.qa.rococo.api.model.PaintingJson;
import guru.qa.rococo.db.entity.artist.ArtistEntity;
import guru.qa.rococo.db.entity.geo.CountryEntity;
import guru.qa.rococo.db.entity.museum.MuseumEntity;
import guru.qa.rococo.db.repository.ArtistRepository;
import guru.qa.rococo.db.repository.MuseumRepository;
import guru.qa.rococo.db.repository.PaintingRepository;
import guru.qa.rococo.db.repository.UserRepository;
import guru.qa.rococo.db.repository.imp.ArtistRepositoryImp;
import guru.qa.rococo.db.repository.imp.MuseumRepositoryImp;
import guru.qa.rococo.db.repository.imp.PaintingRepositoryImp;
import guru.qa.rococo.db.repository.imp.UserRepositoryImp;
import guru.qa.rococo.jupiter.annotation.AroundTestExtension;
import guru.qa.rococo.util.CsvUtils;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.List;

public class DataProviderExtension implements AroundTestExtension {

    private final UserRepository userRepository = new UserRepositoryImp();
    private final ArtistRepository artistRepository = new ArtistRepositoryImp();
    private final MuseumRepository museumRepository = new MuseumRepositoryImp();
    private final PaintingRepository paintingRepository = new PaintingRepositoryImp();
    private final CsvUtils csvUtils = new CsvUtils();

    @Override
    public void beforeAllTests(ExtensionContext extensionContext) {
        //Очистка бд
        userRepository.removeAll();
        artistRepository.removeAll();
        museumRepository.removeAll();
        paintingRepository.removeAll();

        //Создание прекондишена для Художников
        List<ArtistJson> artists = csvUtils.getArtists();
        artists.forEach(artist -> artistRepository.create(new ArtistEntity().fromJson(artist)));

        //Создание прекондишена для Музеев
        List<MuseumJson> museums = csvUtils.getMuseums();
        museums.forEach(museum -> {
            CountryEntity country = museumRepository.findByNameCountry(museum.getGeo().getCountry().getName());
            museum.getGeo().getCountry().setId(country.getId());
            museumRepository.create(MuseumJson.toEntity(museum));
        });


        //Создание прекондишена для Картин
        List<PaintingJson> paintings = csvUtils.getPaintings();
        paintings.forEach(painting -> {
            MuseumEntity museumEntity = museumRepository.findByNameMuseum(painting.getMuseum().getTitle());
            ArtistEntity artistEntity = artistRepository.findByNameArtist(painting.getArtist().getName());
            painting.getMuseum().setId(museumEntity.getId());
            painting.getArtist().setId(artistEntity.getId());
            paintingRepository.create(PaintingJson.toEntity(painting));
        });
    }
}
