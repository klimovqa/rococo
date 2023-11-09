package guru.qa.rococo.util;

import guru.qa.rococo.api.model.ArtistJson;
import guru.qa.rococo.config.Config;
import lombok.SneakyThrows;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CsvUtils {

    private final Config CFG = Config.getInstance();
    private final String PREFIX_PHOTO = "data:image/jpeg;";

    @SneakyThrows
    public List<ArtistJson> getArtists() {
        List<ArtistJson> artists = new ArrayList<>();
        Reader reader = Files.newBufferedReader(Paths.get(CFG.pathArtistCsv()));
        CSVFormat csvFormat = CSVFormat.DEFAULT.withDelimiter(';').withFirstRecordAsHeader();
        CSVParser parser = new CSVParser(reader, csvFormat);

        for (CSVRecord record : parser) {
            ArtistJson artist = new ArtistJson();
            artist.setName(record.get(0));
            artist.setBiography(record.get(1));
            artist.setPhoto(PREFIX_PHOTO + record.get(2));
            artists.add(artist);
        }
        parser.close();
        reader.close();

        return artists;
    }
}
