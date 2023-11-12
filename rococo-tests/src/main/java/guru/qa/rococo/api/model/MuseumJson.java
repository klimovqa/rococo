package guru.qa.rococo.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import guru.qa.rococo.db.entity.museum.LocationEntity;
import guru.qa.rococo.db.entity.museum.MuseumEntity;
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

    public static MuseumEntity toEntity(MuseumJson museum) {
        MuseumEntity museumEntity = new MuseumEntity();
        LocationEntity locationEntity = new LocationEntity();

        //locationEntity.setId(UUID.randomUUID());
        locationEntity.setCity(museum.getGeo().getCity());
        locationEntity.setCountryId(museum.getGeo().getCountry().getId());
        locationEntity.setCountryName(museum.getGeo().getCountry().getName());

        museumEntity.setTitle(museum.getTitle());
        museumEntity.setDescription(museum.getDescription());
        museumEntity.setPhoto(museum.getPhoto());
        museumEntity.setLocation(locationEntity);
        return museumEntity;
    }
}
