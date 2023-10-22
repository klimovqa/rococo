package guru.qa.rococo.controller;

import guru.qa.rococo.model.PaintingJson;
import guru.qa.rococo.service.client.PaintingClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public Page<PaintingJson> painting(@RequestParam(required = false) Integer size,
                                       @RequestParam(required = false) Integer page,
                                       @RequestParam(required = false) String title) {
        if (title != null) {
            return paintingClient.search(title);
        }
        return paintingClient.getPaintings(size, page);
    }

    @GetMapping("/author/{id}")
    public Page<PaintingJson> paintingByAuthor(@PathVariable String id,
                                               @RequestParam Integer size,
                                               @RequestParam Integer page) {
        return paintingClient.getPaintingsByAuthor(id, size, page);
    }

    @GetMapping("{id}")
    public PaintingJson painting(@PathVariable String id) {
        return paintingClient.getPainting(id);
    }

    @PatchMapping
    public PaintingJson updatePainting(@RequestBody PaintingJson paintingJson) {
        return paintingClient.updatePainting(paintingJson);
    }

    @PostMapping
    public PaintingJson addPainting(@RequestBody PaintingJson paintingJson) {
        return paintingClient.addPainting(paintingJson);
    }
}
