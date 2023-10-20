package guru.qa.rococo.controller;

import guru.qa.rococo.model.MuseumJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/painting")
@Slf4j
public class PaintingController {

    @GetMapping
    public void painting(@RequestParam int size,
                                   @RequestParam int page) {
    }
}
