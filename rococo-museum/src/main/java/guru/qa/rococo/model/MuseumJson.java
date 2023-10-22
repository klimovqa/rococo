package guru.qa.rococo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import guru.qa.rococo.data.MuseumEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;


@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class MuseumJson {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("photo")
    private String photo;
    @JsonProperty("geo")
    private GeoJson geo;

    public static MuseumJson toJson(MuseumEntity entity) {
        MuseumJson museum = new MuseumJson();
        museum.setId(entity.getId());
        museum.setTitle(entity.getTitle());
        museum.setDescription(entity.getDescription());
        museum.setPhoto(entity.getPhoto());
        museum.setGeo(new GeoJson()
                .setCity(entity.getLocation().getCity())
                .setCountry(new CountryJson()
                        .setId(entity.getLocation().getCountryId())
                        .setName(entity.getLocation().getCountryName())));
        return museum;
    }
}
