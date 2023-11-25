package guru.qa.rococo.config;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.chrome.ChromeOptions;

public class DockerConfig implements Config {

    static final DockerConfig config = new DockerConfig();

    static {
        Configuration.browserSize = "1920x1200";
        Configuration.remote = "http://selenoid:4444/wd/hub";
        Configuration.timeout = 10000;
        Configuration.browser = "chrome";
        Configuration.browserVersion = "110.0";
        Configuration.browserCapabilities = new ChromeOptions().addArguments("--no-sandbox");

    }

    private DockerConfig() {
    }

    @Override
    public String baseUrl() {
        return "http://client.rococo.dc";
    }

    @Override
    public String databaseHost() {
        return "rococo-all-db";
    }

    @Override
    public String rococoFrontUrl() {
        return "http://client.rococo.dc";
    }

    @Override
    public String rococoGeoUrl() {
        return "http://geo.rococo.dc:8087/";
    }

    @Override
    public String rococoArtistUrl() {
        return "http://artist.rococo.dc:8086/";
    }

    @Override
    public String rococoMuseumUrl() {
        return "http://museum.rococo.dc:8088/";
    }

    @Override
    public String rococoPaintingUrl() {
        return "http://painting.rococo.dc:8085/";
    }


    @Override
    public String rococoAuthUrl() {
        return "http://auth.rococo.dc:9000";
    }

    @Override
    public String rococoUserDataUrl() {
        return "http://userdata.rococo.dc:8089/";
    }

    @Override
    public String rococoGatewayUrl() {
        return "http://api.rococo.dc:8080/";
    }
}
