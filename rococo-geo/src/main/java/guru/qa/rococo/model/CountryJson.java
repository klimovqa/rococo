package guru.qa.rococo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import guru.qa.rococo.data.CountryEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;


@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class CountryJson {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("name")
    private String name;

    public static CountryJson fromEntity(CountryEntity entity) {
        CountryJson json = new CountryJson();
        json.setId(entity.getId());
        json.setName(entity.getName());
        return json;
    }
}
