package guru.qa.rococo.config;

import java.util.Objects;

public interface Config {

    ClassLoader classLoader = Config.class.getClassLoader();
    String ARTIST_PATH = "data/artist.csv";
    String MUSEUM_PATH = "data/museum.csv";
    String PAINTING_PATH = "data/painting.csv";

    static Config getInstance() {
        if ("docker".equals(System.getProperty("test.env"))) {
            return DockerConfig.config;
        }
        return LocalConfig.config;
    }


    String baseUrl();

    String databaseHost();

    String rococoFrontUrl();

    String rococoGeoUrl();

    String rococoArtistUrl();

    String rococoMuseumUrl();

    String rococoPaintingUrl();

    String rococoAuthUrl();

    String rococoUserDataUrl();

    String rococoGatewayUrl();

    default String databaseUser() {
        return "postgres";
    }

    default String databasePassword() {
        return "secret";
    }

    default int databasePort() {
        return 5432;
    }

    default String pathArtistCsv() {
        return Objects.requireNonNull(classLoader.getResource(ARTIST_PATH)).getPath();
    }
    default String pathMuseumCsv() {
        return Objects.requireNonNull(classLoader.getResource(MUSEUM_PATH)).getPath();
    }
    default String pathPaintingCsv() {
        return Objects.requireNonNull(classLoader.getResource(PAINTING_PATH)).getPath();
    }


}
