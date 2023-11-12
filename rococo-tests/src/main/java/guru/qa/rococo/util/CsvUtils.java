package guru.qa.rococo.util;

import guru.qa.rococo.api.model.*;
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
            artist.setPhoto(record.get(2));
            artists.add(artist);
        }
        parser.close();
        reader.close();

        return artists;
    }

    @SneakyThrows
    public List<MuseumJson> getMuseums() {
        List<MuseumJson> museums = new ArrayList<>();
        Reader reader = Files.newBufferedReader(Paths.get(CFG.pathMuseumCsv()));
        CSVFormat csvFormat = CSVFormat.DEFAULT.withDelimiter(';').withFirstRecordAsHeader();
        CSVParser parser = new CSVParser(reader, csvFormat);

        for (CSVRecord record : parser) {
            MuseumJson museum = new MuseumJson();
            GeoJson geoJson = new GeoJson();
            CountryJson countryJson = new CountryJson();

            countryJson.setName(record.get(2));
            geoJson.setCity(record.get(3));
            geoJson.setCountry(countryJson);

            museum.setTitle(record.get(0));
            museum.setDescription(record.get(1));
            museum.setPhoto(record.get(4));
            museum.setGeo(geoJson);
            museums.add(museum);
        }
        parser.close();
        reader.close();

        return museums;
    }

    @SneakyThrows
    public List<PaintingJson> getPaintings() {
        List<PaintingJson> paintings = new ArrayList<>();
        Reader reader = Files.newBufferedReader(Paths.get(CFG.pathPaintingCsv()));
        CSVFormat csvFormat = CSVFormat.DEFAULT.withDelimiter(';').withFirstRecordAsHeader();
        CSVParser parser = new CSVParser(reader, csvFormat);

        for (CSVRecord record : parser) {
            PaintingJson painting = new PaintingJson();
            MuseumJson museum = new MuseumJson();
            ArtistJson artist = new ArtistJson();

            museum.setTitle(record.get(3));
            artist.setName(record.get(4));

            painting.setTitle(record.get(0));
            painting.setDescription(record.get(1));
            painting.setContent(record.get(2));
            painting.setMuseum(museum);
            painting.setArtist(artist);
            paintings.add(painting);
        }
        parser.close();
        reader.close();

        return paintings;
    }

}
