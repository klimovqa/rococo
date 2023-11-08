package guru.qa.rococo.db.entity.artist;

import guru.qa.rococo.api.model.ArtistJson;
import jakarta.persistence.*;
import lombok.Data;

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

    public ArtistEntity fromJson(ArtistJson artist) {
        this.setName(artist.getName());
        this.setBiography(artist.getBiography());
        this.setPhoto(artist.getPhoto());
        return this;
    }
}