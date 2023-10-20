package guru.qa.rococo.controller;

import guru.qa.rococo.model.MuseumJson;
import guru.qa.rococo.service.MuseumClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
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
    public Page<MuseumJson> museum(@AuthenticationPrincipal Jwt principal,
                                   @RequestParam int size,
                                   @RequestParam int page) {
        return museumClient.getMuseums(size, page);
    }

    @GetMapping("{id}")
    public MuseumJson museum(@AuthenticationPrincipal Jwt principal,
                                   @PathVariable String id) {
        return museumClient.getMuseum(id);
    }

    @PatchMapping
    public MuseumJson updateMuseum(@AuthenticationPrincipal Jwt principal,
                                   @RequestBody MuseumJson museumJson) {
        return museumClient.updateMuseum(museumJson);
    }

    @PostMapping
    public MuseumJson addMuseum(@AuthenticationPrincipal Jwt principal,
                                   @RequestBody MuseumJson museumJson){
        return museumClient.addMuseum(museumJson);
    }
}
