package guru.qa.rococo.config;

public interface Config {

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
        return "rococo-tests/src/main/resources/data/artist.csv";
    }
    default String pathMuseumCsv() {
        return "rococo-tests/src/main/resources/data/museum.csv";
    }
    default String pathPaintingCsv() {
        return "rococo-tests/src/main/resources/data/painting.csv";
    }


}
