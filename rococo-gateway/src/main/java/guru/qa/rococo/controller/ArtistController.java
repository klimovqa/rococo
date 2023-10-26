package guru.qa.rococo.controller;

import guru.qa.rococo.model.ArtistJson;
import guru.qa.rococo.service.client.ArtistClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/artist")
@Slf4j
public class ArtistController {


    private final ArtistClient client;

    @Autowired
    public ArtistController(ArtistClient client) {
        this.client = client;
    }

    @GetMapping
    public Page<ArtistJson> findAll(@RequestParam(required = false) String name,
                                    @PageableDefault Pageable pageable) {
        return client.findAll(name, pageable);
    }

    @GetMapping("{id}")
    public ArtistJson findById(@PathVariable String id) {
        return client.findById(id);
    }

    @PatchMapping
    public ArtistJson update(@RequestBody ArtistJson artist) {
        return client.update(artist);
    }

    @PostMapping
    public ArtistJson add(@RequestBody ArtistJson artist){
        return client.add(artist);
    }
}
