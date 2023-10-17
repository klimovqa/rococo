package guru.qa.rococo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;


@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class GeoJson {
    @JsonProperty("countries")
    private List<CountryJson> country;
}
