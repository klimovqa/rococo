package guru.qa.rococo.jupiter.extension;

import guru.qa.rococo.api.model.ArtistJson;
import guru.qa.rococo.db.dao.ArtistDAO;
import guru.qa.rococo.db.dao.impl.ArtistDAOHibernate;
import guru.qa.rococo.db.entity.artist.ArtistEntity;
import guru.qa.rococo.jupiter.annotation.AroundTestExtension;
import guru.qa.rococo.util.CsvUtils;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.List;

public class DataProviderExtension implements AroundTestExtension {

    @Override
    public void beforeAllTests(ExtensionContext extensionContext) {
        CsvUtils csvUtils = new CsvUtils();
        List<ArtistJson> artists = csvUtils.getArtists();
        ArtistDAO artistDAO = new ArtistDAOHibernate();
        artists.forEach(a -> artistDAO.createArtist(new ArtistEntity().fromJson(a)));
    }
}
