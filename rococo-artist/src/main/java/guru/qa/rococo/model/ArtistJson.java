package guru.qa.rococo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import guru.qa.rococo.data.ArtistEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.modelmapper.ModelMapper;

import java.util.UUID;


@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class ArtistJson {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("biography")
    private String biography;
    @JsonProperty("photo")
    private String photo;


    public ArtistJson fromEntity() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, ArtistJson.class);
    }

    public static ArtistJson fromEntity(ArtistEntity entity) {
        ArtistJson museum = new ArtistJson();
        museum.setId(entity.getId());
        museum.setName(entity.getName());
        museum.setBiography(entity.getBiography());
        museum.setPhoto(entity.getPhoto());
        return museum;
    }
}
