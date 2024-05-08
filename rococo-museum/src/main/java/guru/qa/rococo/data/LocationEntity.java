package guru.qa.rococo.data;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Entity
@Table(name = "location")
@Data
@EqualsAndHashCode
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