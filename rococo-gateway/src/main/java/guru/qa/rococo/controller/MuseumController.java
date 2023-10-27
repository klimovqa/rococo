package guru.qa.rococo.controller;

import guru.qa.rococo.model.MuseumJson;
import guru.qa.rococo.service.client.MuseumClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/museum")
@Slf4j
public class MuseumController {


    private final MuseumClient client;

    @Autowired
    public MuseumController(MuseumClient client) {
        this.client = client;
    }

    @GetMapping
    public Page<MuseumJson> museum(@PageableDefault Pageable pageable,
                                   @RequestParam(required = false) String title) {
        return client.findAll(title, pageable);
    }

    @GetMapping("{id}")
    public MuseumJson museum(@PathVariable String id) {
        return client.findById(id);
    }

    @PatchMapping
    public MuseumJson updateMuseum(@RequestBody MuseumJson museumJson) {
        return client.update(museumJson);
    }

    @PostMapping
    public MuseumJson addMuseum(@RequestBody MuseumJson museumJson){
        return client.add(museumJson);
    }
}
