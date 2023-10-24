package guru.qa.rococo.controller;

import guru.qa.rococo.model.ArtistJson;
import guru.qa.rococo.service.client.ArtistClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/artist")
@Slf4j
public class ArtistController {


    private final ArtistClient artistClient;

    @Autowired
    public ArtistController(ArtistClient artistClient) {
        this.artistClient = artistClient;
    }

    @GetMapping
    public Page<ArtistJson> artist(@RequestParam(required = false) Integer size,
                                   @RequestParam(required = false) Integer page,
                                   @RequestParam(required = false) String name) {
        if (name != null) {
            return artistClient.search(name);
        }
        return artistClient.getArtists(size, page);
    }

    @GetMapping("{id}")
    public ArtistJson artist(@PathVariable String id) {
        return artistClient.getArtist(id);
    }

    @PatchMapping
    public ArtistJson updateArtist(@RequestBody ArtistJson artistJson) {
        return artistClient.updateArtist(artistJson);
    }

    @PostMapping
    public ArtistJson addArtist(@RequestBody ArtistJson artistJson){
        return artistClient.addArtist(artistJson);
    }
}
