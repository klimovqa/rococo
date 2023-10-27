package guru.qa.rococo.controller;

import guru.qa.rococo.model.PaintingJson;
import guru.qa.rococo.service.client.PaintingClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/painting")
@Slf4j
public class PaintingController {


    private final PaintingClient paintingClient;

    @Autowired
    public PaintingController(PaintingClient paintingClient) {
        this.paintingClient = paintingClient;
    }

    @GetMapping
    public Page<PaintingJson> findAll(@PageableDefault Pageable pageable,
                                      @RequestParam(required = false) String title) {
        return paintingClient.findAll(title, pageable);
    }

    @GetMapping("/author/{uuid}")
    public Page<PaintingJson> findByAuthor(@PageableDefault Pageable pageable,
                                           @PathVariable String uuid) {
        return paintingClient.findByAuthor(uuid, pageable);
    }

    @GetMapping("{id}")
    public PaintingJson findById(@PathVariable String id) {
        return paintingClient.findById(id);
    }

    @PatchMapping
    public PaintingJson update(@RequestBody PaintingJson painting) {
        return paintingClient.update(painting);
    }

    @PostMapping
    public PaintingJson add(@RequestBody PaintingJson painting) {
        return paintingClient.add(painting);
    }
}
