package guru.qa.rococo.controller;


import guru.qa.rococo.model.UserJson;
import guru.qa.rococo.service.client.UserDataClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user")
public class UserController {


    private final UserDataClient client;

    @Autowired
    public UserController(UserDataClient client) {
        this.client = client;
    }


    @GetMapping
    public UserJson findByUsername(@AuthenticationPrincipal Jwt principal) {
        String username = principal.getClaim("sub");
        return client.findByUsername(username);
    }

    @PatchMapping
    public UserJson update(@AuthenticationPrincipal Jwt principal,
                           @RequestBody UserJson user) {
        String username = principal.getClaim("sub");
        user.setUsername(username);
        return client.update(user);
    }

}
