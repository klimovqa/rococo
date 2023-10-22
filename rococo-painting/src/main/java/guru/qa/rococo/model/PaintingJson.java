package guru.qa.rococo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import guru.qa.rococo.data.PaintingEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;


@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class PaintingJson {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("content")
    private String content;
    @JsonProperty("museum")
    private MuseumJson museum;
    @JsonProperty("artist")
    private ArtistJson artist;

    public static PaintingJson toJson(PaintingEntity entity) {
        PaintingJson painting = new PaintingJson();
        ArtistJson artist = new ArtistJson();
        MuseumJson museum = new MuseumJson();
        painting.setId(entity.getId());
        painting.setTitle(entity.getTitle());
        painting.setDescription(entity.getDescription());
        painting.setContent(entity.getContent());
        artist.setId(entity.getArtistId());
        museum.setId(entity.getMuseumId());
        painting.setArtist(artist);
        painting.setMuseum(museum);
        return painting;
    }

}
