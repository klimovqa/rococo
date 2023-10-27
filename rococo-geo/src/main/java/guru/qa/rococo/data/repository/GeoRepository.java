package guru.qa.rococo.data.repository;

import guru.qa.rococo.data.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface GeoRepository extends JpaRepository<CountryEntity, UUID> {

    Optional<CountryEntity> findByName(String nameCountry);
}
