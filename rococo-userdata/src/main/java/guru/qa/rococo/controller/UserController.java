package guru.qa.rococo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import guru.qa.rococo.model.UserJson;
import guru.qa.rococo.service.UserDataService;


@Slf4j
@RestController
@RequestMapping("/api/userdata")
public class UserController {


    private final UserDataService service;

    @Autowired
    public UserController(UserDataService service) {
        this.service = service;
    }

    @GetMapping("/user")
    public UserJson findByUsername(@RequestParam String username) {
        return UserJson.fromEntity(service.findByUsername(username));
    }
    @PatchMapping("/update")
    public UserJson update(@RequestBody UserJson user) {
        return UserJson.fromEntity(service.update(user));
    }

}
