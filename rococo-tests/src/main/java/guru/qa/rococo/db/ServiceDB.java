package guru.qa.rococo.db;

import guru.qa.rococo.config.Config;

import static org.apache.commons.lang3.StringUtils.substringAfter;

public enum ServiceDB {
    AUTH("jdbc:postgresql://%s:%d/rococo-auth"),
    USERDATA("jdbc:postgresql://%s:%d/rococo-userdata"),
    GEO("jdbc:postgresql://%s:%d/rococo-geo"),
    MUSEUM("jdbc:postgresql://%s:%d/rococo-museum"),
    ARTIST("jdbc:postgresql://%s:%d/rococo-artist"),
    PAINTING("jdbc:postgresql://%s:%d/rococo-painting");

    private final String url;
    private static final Config cfg = Config.getInstance();

    ServiceDB(String url) {
        this.url = url;
    }

    public String getUrl() {
        return String.format(
                url,
                cfg.databaseHost(),
                cfg.databasePort()
        );
    }

    public String p6spyUrl() {
        return "jdbc:p6spy:postgresql:" + substringAfter(getUrl(), "jdbc:postgresql:");
    }

}
