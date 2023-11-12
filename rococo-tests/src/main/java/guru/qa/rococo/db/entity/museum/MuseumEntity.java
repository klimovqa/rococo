package guru.qa.rococo.db.entity.museum;

import guru.qa.rococo.api.model.CountryJson;
import guru.qa.rococo.api.model.GeoJson;
import guru.qa.rococo.api.model.MuseumJson;
import jakarta.persistence.*;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.UUID;

@Entity
@Table(name = "museum")
@Data
public class MuseumEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, columnDefinition = "UUID default gen_random_uuid()")
    private UUID id;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private String photo;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private LocationEntity location;


    public MuseumJson toJson() {
        ModelMapper modelMapper = new ModelMapper();
        GeoJson geo = modelMapper.map(this.location, GeoJson.class);
        MuseumJson museum = modelMapper.map(this, MuseumJson.class);
        museum.setGeo(geo);
        return museum;
    }

    public MuseumEntity fromJson(MuseumJson museum) {
        this.setTitle(museum.getTitle());
        this.setDescription(museum.getDescription());
        this.setPhoto(museum.getPhoto());
        CountryJson country = museum.getGeo().getCountry();
        if (this.location != null
                && !(country.getId().equals(this.location.getCountryId())
                && country.getName().equals(this.location.getCountryName()))) {
            this.getLocation().setCountryName(country.getName());
            this.getLocation().setCountryId(country.getId());
            this.getLocation().setCity(museum.getGeo().getCity());
        }

        if (this.location == null) {
            LocationEntity loc = new LocationEntity();
            loc.setCountryName(country.getName());
            loc.setCountryId(country.getId());
            loc.setCity(museum.getGeo().getCity());
            this.setLocation(loc);
        }
        return this;
    }
}