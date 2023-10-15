package guru.qa.rococo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserJson {
    @JsonProperty("username")
    private String username;
}
