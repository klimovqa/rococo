package guru.qa.rococo.data;

import guru.qa.rococo.model.CountryJson;
import guru.qa.rococo.model.GeoJson;
import guru.qa.rococo.model.MuseumJson;
import guru.qa.rococo.model.PaintingJson;
import jakarta.persistence.*;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.UUID;

@Entity
@Table(name = "painting")
@Data
public class PaintingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, columnDefinition = "UUID default gen_random_uuid()")
    private UUID id;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private String content;

    @Column(name = "museum_id")
    private UUID museumId;

    @Column(name = "artist_id")
    private UUID artistId;


    public PaintingJson toJson() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, PaintingJson.class);
    }

    public PaintingEntity fromJson(PaintingJson painting) {
        this.setTitle(painting.getTitle());
        this.setDescription(painting.getDescription());
        this.setContent(painting.getContent());
        this.setArtistId(painting.getArtist().getId());
        this.setMuseumId(painting.getMuseum().getId());
        return this;
    }
}