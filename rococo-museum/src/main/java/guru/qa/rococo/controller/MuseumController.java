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
    public Page<MuseumJson> getMuseums(@PageableDefault Pageable pageable) {
        Page<MuseumEntity> partMuseums = service.getMuseums(pageable);
        return partMuseums.map(MuseumEntity::toMuseumJson);
    }

    @GetMapping("{id}")
    public MuseumJson getMuseumById(@PathVariable String id) {
        return MuseumJson.toJson(service.getMuseumById(id));
    }

    @GetMapping("/search")
    public Page<MuseumJson> search(@PageableDefault Pageable pageable,
                                   @RequestParam String title) {
        Page<MuseumEntity> partMuseums = service.getMuseumByTitle(pageable, title);
        return partMuseums.map(MuseumEntity::toMuseumJson);
    }

    @PatchMapping
    public MuseumJson updateMuseum(@RequestBody MuseumJson museum) {
        return MuseumJson.toJson(service.updateMuseum(museum));
    }

    @PostMapping
    public MuseumJson addMuseum(@RequestBody MuseumJson museum) {
        return MuseumJson.toJson(service.addMuseum(museum));
    }

}