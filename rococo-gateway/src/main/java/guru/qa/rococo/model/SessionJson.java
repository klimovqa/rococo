package guru.qa.rococo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class SessionJson {

    @JsonProperty("username")
    private String username;
    @JsonProperty("issuedAt")
    private Date issuedAt;
    @JsonProperty("expiresAt")
    private Date expiresAt;

    public static @Nonnull SessionJson empty() {
        return new SessionJson(null, null, null);
    }
}
