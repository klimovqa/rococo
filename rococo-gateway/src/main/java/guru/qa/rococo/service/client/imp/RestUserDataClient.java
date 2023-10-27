package guru.qa.rococo.service.client.imp;

import guru.qa.rococo.model.UserJson;
import guru.qa.rococo.service.client.UserDataClient;
import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static java.util.Objects.*;

@Component
public class RestUserDataClient implements UserDataClient {

    private final WebClient webClient;
    private final String uriUserDataService;

    @Autowired
    public RestUserDataClient(WebClient webClient,
                              @Value("${rococo-userdata.base-uri}") String uriUserDataService) {
        this.webClient = webClient;
        this.uriUserDataService = uriUserDataService;
    }


    @Override
    public @Nonnull
    UserJson findByUsername(@Nonnull String username) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("username", username);
        URI uri = UriComponentsBuilder
                .fromHttpUrl(uriUserDataService + "/api/userdata/user")
                .queryParams(params)
                .build()
                .toUri();

        return requireNonNull(webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(UserJson.class)
                .block());
    }

    @Nonnull
    @Override
    public UserJson update(@Nonnull UserJson user) {
        URI uri = UriComponentsBuilder
                .fromHttpUrl(uriUserDataService + "/api/userdata/update")
                .build()
                .toUri();

        return requireNonNull(webClient.patch()
                .uri(uri)
                .bodyValue(user)
                .retrieve()
                .bodyToMono(UserJson.class)
                .block());
    }

}
