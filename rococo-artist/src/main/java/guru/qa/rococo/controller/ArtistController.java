package guru.qa.rococo.controller;

import guru.qa.rococo.data.ArtistEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import guru.qa.rococo.model.ArtistJson;
import guru.qa.rococo.service.ArtistService;

import java.util.UUID;


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
    public Page<ArtistJson> findALl(@PageableDefault Pageable pageable) {
        Page<ArtistEntity> all = service.findAll(pageable);
        return all.map(ArtistJson::fromEntity);
    }

    @GetMapping("{id}")
    public ResponseEntity<ArtistJson> findById(@PathVariable String id) {
        return new ResponseEntity<>(ArtistJson
                .fromEntity(service.findById(UUID.fromString(id))), HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public Page<ArtistJson> findByName(@PageableDefault Pageable pageable,
                                       @PathVariable String name) {
        Page<ArtistEntity> all = service.findByName(pageable, name);
        return all.map(ArtistJson::fromEntity);
    }

    @PatchMapping
    public ArtistJson update(@RequestBody ArtistJson artist) {
        return ArtistJson.fromEntity(service.update(artist));
    }

    @PostMapping
    public ArtistJson add(@RequestBody ArtistJson artist) {
        return ArtistJson.fromEntity(service.add(artist));
    }

}
