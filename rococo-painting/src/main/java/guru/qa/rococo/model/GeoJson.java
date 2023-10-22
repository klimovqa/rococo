package guru.qa.rococo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@Accessors(chain = true)
public class GeoJson {

    @JsonProperty("city")
    private String city;
    @JsonProperty("country")
    private CountryJson country;
}
