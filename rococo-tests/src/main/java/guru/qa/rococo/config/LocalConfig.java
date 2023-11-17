package guru.qa.rococo.config;


import com.codeborne.selenide.Configuration;

public class LocalConfig implements Config {

    static final LocalConfig config = new LocalConfig();

    static {
        Configuration.browserSize = "1980x1024";
        Configuration.headless = true;
    }

    private LocalConfig() {
    }

    @Override
    public String baseUrl() {
        return "http://127.0.0.1:3000";
    }

    @Override
    public String databaseHost() {
        return "localhost";
    }

    @Override
    public String rococoFrontUrl() {
        return "http://127.0.0.1:3000";
    }

    @Override
    public String rococoGeoUrl() {
        return "http://127.0.0.1:8087";
    }

    @Override
    public String rococoArtistUrl() {
        return "http://127.0.0.1:8086";
    }

    @Override
    public String rococoMuseumUrl() {
        return "http://127.0.0.1:8086";
    }

    @Override
    public String rococoPaintingUrl() {
        return "http://127.0.0.1:8085";
    }

    @Override
    public String rococoAuthUrl() {
        return "http://127.0.0.1:9000";
    }

    @Override
    public String rococoUserDataUrl() {
        return "http://127.0.0.1:8089";
    }

    @Override
    public String rococoGatewayUrl() {
        return "http://127.0.0.1:8080";
    }
}
