package guru.qa.rococo.controller;

import guru.qa.rococo.data.ArtistEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import guru.qa.rococo.model.ArtistJson;
import guru.qa.rococo.service.ArtistService;


@Slf4j
@RestController
@RequestMapping("/api/artist")
public class ArtistController {

    private final ArtistService service;

    @Autowired
    public ArtistController(ArtistService service) {
        this.service = service;
    }

    @GetMapping
    public Page<ArtistJson> getArtists(@PageableDefault Pageable pageable) {
        Page<ArtistEntity> partArtist = service.getArtist(pageable);
        return partArtist.map(ArtistJson::toJson);
    }

    @GetMapping("{id}")
    public ArtistJson getArtistById(@PathVariable String id) {
        return ArtistJson.toJson(service.getArtistById(id));
    }

    @GetMapping("/search")
    public Page<ArtistJson> search(@PageableDefault Pageable pageable,
                                   @RequestParam String name) {
        Page<ArtistEntity> partMuseums = service.getArtistByName(pageable, name);
        return partMuseums.map(ArtistEntity::toArtistJson);
    }

    @PatchMapping
    public ArtistJson updateArtist(@RequestBody ArtistJson artist) {
        return ArtistJson.toJson(service.updateArtist(artist));
    }

    @PostMapping
    public ArtistJson addMuseum(@RequestBody ArtistJson museum) {
        return ArtistJson.toJson(service.addArtist(museum));
    }

}
