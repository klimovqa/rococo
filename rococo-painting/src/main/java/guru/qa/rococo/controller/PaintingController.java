package guru.qa.rococo.controller;

import guru.qa.rococo.data.PaintingEntity;
import guru.qa.rococo.model.PaintingJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import guru.qa.rococo.service.PaintingService;

import java.util.UUID;


@Slf4j
@RestController
@RequestMapping("/api/painting")
public class PaintingController {

    private final PaintingService service;

    @Autowired
    public PaintingController(PaintingService service) {
        this.service = service;
    }

    @GetMapping
    public Page<PaintingJson> findAll(@PageableDefault Pageable pageable) {
        Page<PaintingEntity> all = service.findAll(pageable);
        return all.map(PaintingEntity::toJson);
    }

    @GetMapping("{uuid}")
    public PaintingJson findById(@PathVariable String uuid) {
        return PaintingJson.toJson(service.findById(UUID.fromString(uuid)));
    }

    @GetMapping("/title/{name}")
    public Page<PaintingJson> findByTitle(@PageableDefault Pageable pageable,
                                          @PathVariable String name) {
        Page<PaintingEntity> all = service.findByTitle(pageable, name);
        return all.map(PaintingEntity::toJson);
    }

    @GetMapping("/author/{uuid}")
    public Page<PaintingJson> findByAuthor(@PathVariable String uuid,
                                           @PageableDefault Pageable pageable) {
        Page<PaintingEntity> all = service.findByAuthor(UUID.fromString(uuid), pageable);
        return all.map(PaintingEntity::toJson);
    }

    @PatchMapping
    public PaintingJson update(@RequestBody PaintingJson paining) {
        return PaintingJson.toJson(service.update(paining));
    }

    @PostMapping
    public PaintingJson add(@RequestBody PaintingJson paining) {
        return PaintingJson.toJson(service.add(paining));
    }

}
