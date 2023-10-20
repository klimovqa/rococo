package guru.qa.rococo.controller;

import guru.qa.rococo.model.MuseumJson;
import guru.qa.rococo.service.MuseumClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/museum")
@Slf4j
public class MuseumController {


    private final MuseumClient museumClient;

    @Autowired
    public MuseumController(MuseumClient museumClient) {
        this.museumClient = museumClient;
    }

    @GetMapping
    public Page<MuseumJson> museum(@RequestParam(required = false) Integer size,
                                   @RequestParam(required = false) Integer page,
                                   @RequestParam(required = false) String title) {
        if (title != null) {
            return museumClient.search(title);
        }
        return museumClient.getMuseums(size, page);
    }

    @GetMapping("{id}")
    public MuseumJson museum(@PathVariable String id) {
        return museumClient.getMuseum(id);
    }

    @PatchMapping
    public MuseumJson updateMuseum(@RequestBody MuseumJson museumJson) {
        return museumClient.updateMuseum(museumJson);
    }

    @PostMapping
    public MuseumJson addMuseum(@RequestBody MuseumJson museumJson){
        return museumClient.addMuseum(museumJson);
    }
}
