package guru.qa.rococo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@Accessors(chain = true)
public class CountryJson {

    @JsonProperty("id")
    private UUID id;
    @JsonProperty("name")
    private String name;
}
