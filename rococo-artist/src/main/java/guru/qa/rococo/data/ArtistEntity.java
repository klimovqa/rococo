package guru.qa.rococo.data;

import guru.qa.rococo.model.ArtistJson;
import jakarta.persistence.*;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.UUID;

@Entity
@Table(name = "artist")
@Data
public class ArtistEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, columnDefinition = "UUID default gen_random_uuid()")
    private UUID id;

    @Column
    private String name;

    @Column
    private String biography;

    @Column
    private String photo;

    public ArtistJson toArtistJson() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, ArtistJson.class);
    }

    public ArtistEntity fromJson(ArtistJson artist) {
        this.setName(artist.getName());
        this.setBiography(artist.getBiography());
        this.setPhoto(artist.getPhoto());
        return this;
    }
}