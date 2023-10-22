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
    public Page<PaintingJson> getPainting(@PageableDefault Pageable pageable) {
        Page<PaintingEntity> partPainting = service.getPainting(pageable);
        return partPainting.map(PaintingEntity::toJson);
    }
    @GetMapping("/author/{id}")
    public Page<PaintingJson> getPaintingByAuthor(@PathVariable String id,
                                                  @PageableDefault Pageable pageable) {
        Page<PaintingEntity> partPainting = service.getPaintingByAuthor(id, pageable);
        return partPainting.map(PaintingEntity::toJson);
    }

    @GetMapping("{id}")
    public PaintingJson getPaintingById(@PathVariable String id) {
        return PaintingJson.toJson(service.getPaintingById(id));
    }

    @GetMapping("/search")
    public Page<PaintingJson> search(@PageableDefault Pageable pageable,
                                   @RequestParam String title) {
        Page<PaintingEntity> partPainting = service.getPaintingByTitle(pageable, title);
        return partPainting.map(PaintingEntity::toJson);
    }

    @PatchMapping
    public PaintingJson updatePainting(@RequestBody PaintingJson paining) {
        return PaintingJson.toJson(service.updatePainting(paining));
    }

    @PostMapping
    public PaintingJson addPainting(@RequestBody PaintingJson paining) {
        return PaintingJson.toJson(service.addPainting(paining));
    }

}
