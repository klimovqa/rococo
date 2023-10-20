package guru.qa.rococo.data;

import guru.qa.rococo.model.GeoJson;
import guru.qa.rococo.model.MuseumJson;
import jakarta.persistence.*;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.UUID;

@Entity
@Table(name = "location")
@Data
public class LocationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, columnDefinition = "UUID default gen_random_uuid()")
    private UUID id;

    @Column(name = "country_id")
    private UUID countryId;

    @Column(name = "country_name")
    private String countryName;

    @Column
    private String city;
}