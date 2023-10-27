package guru.qa.rococo.controller;

import guru.qa.rococo.data.MuseumEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import guru.qa.rococo.model.MuseumJson;
import guru.qa.rococo.service.MuseumService;

import java.util.UUID;


@Slf4j
@RestController
@RequestMapping("/api/museum")
public class MuseumController {

    private final MuseumService service;

    @Autowired
    public MuseumController(MuseumService service) {
        this.service = service;
    }

    @GetMapping
    public Page<MuseumJson> findAll(@PageableDefault Pageable pageable) {
        Page<MuseumEntity> all = service.findAll(pageable);
        return all.map(MuseumEntity::toJson);
    }

    @GetMapping("{id}")
    public MuseumJson findById(@PathVariable String id) {
        return MuseumJson.fromEntity(service.findById(UUID.fromString(id)));
    }

    @GetMapping("/title/{title}")
    public Page<MuseumJson> findByTitle(@PageableDefault Pageable pageable,
                                        @PathVariable String title) {
        Page<MuseumEntity> all = service.findByTitle(pageable, title);
        return all.map(MuseumEntity::toJson);
    }

    @PatchMapping
    public MuseumJson update(@RequestBody MuseumJson museum) {
        return MuseumJson.fromEntity(service.update(museum));
    }

    @PostMapping
    public MuseumJson add(@RequestBody MuseumJson museum) {
        return MuseumJson.fromEntity(service.add(museum));
    }

}
