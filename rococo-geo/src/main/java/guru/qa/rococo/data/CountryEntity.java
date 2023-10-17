package guru.qa.rococo.data;

import guru.qa.rococo.model.CountryJson;
import jakarta.persistence.*;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.UUID;

@Entity
@Table(name = "country")
@Data
public class CountryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, columnDefinition = "UUID default gen_random_uuid()")
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    public static CountryEntity fromJson(CountryJson country) {
        CountryEntity entity = new CountryEntity();
        entity.setId(country.getId());
        entity.setName(country.getName());
        return entity;
    }

    public CountryJson toCountryJson() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, CountryJson.class);
    }

}