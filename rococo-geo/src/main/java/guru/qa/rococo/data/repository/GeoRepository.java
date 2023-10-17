package guru.qa.rococo.data.repository;

import guru.qa.rococo.data.CountryEntity;
import jakarta.annotation.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface GeoRepository extends JpaRepository<CountryEntity, UUID> {

    @Nullable
    Optional<CountryEntity> findByName(String nameCountry);
}
