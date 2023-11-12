package guru.qa.rococo.db.entity.painting;

import jakarta.persistence.*;
import lombok.Data;

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
}