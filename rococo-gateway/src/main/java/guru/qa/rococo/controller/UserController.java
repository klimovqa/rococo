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


    private final UserDataClient userDataClient;

    @Autowired
    public UserController(UserDataClient userDataClient) {
        this.userDataClient = userDataClient;
    }


    @GetMapping
    public UserJson getUser(@AuthenticationPrincipal Jwt principal) {
        String username = principal.getClaim("sub");
        return userDataClient.currentUser(username);
    }

    @PatchMapping
    public UserJson updateUser(@AuthenticationPrincipal Jwt principal,
                               @RequestBody UserJson userJson) {
        String username = principal.getClaim("sub");
        userJson.setUsername(username);
        return userDataClient.updateUser(userJson);
    }

}
