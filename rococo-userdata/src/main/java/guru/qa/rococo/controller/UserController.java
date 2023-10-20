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

    @GetMapping("/currentUser")
    public UserJson currentUserInfo(@RequestParam String username) {
        return UserJson.toUserJson(service.getCurrentUser(username));
    }
    @PatchMapping("/updateUser")
    public UserJson updateUserInfo(@RequestBody UserJson userJson) {
        return UserJson.toUserJson(service.updateUser(userJson));
    }

}
