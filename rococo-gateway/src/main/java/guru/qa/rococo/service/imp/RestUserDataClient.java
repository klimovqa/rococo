package guru.qa.rococo.service.imp;

import guru.qa.rococo.model.UserJson;
import guru.qa.rococo.service.UserDataClient;
import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class RestUserDataClient implements UserDataClient {

    private final WebClient webClient;
    private final String rococoUserdataBaseUri;

    @Autowired
    public RestUserDataClient(WebClient webClient,
                              @Value("${rococo-userdata.base-uri}") String rococoUserdataBaseUri) {
        this.webClient = webClient;
        this.rococoUserdataBaseUri = rococoUserdataBaseUri;
    }


    @Override
    public @Nonnull
    UserJson currentUser(@Nonnull String username) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("username", username);
        URI uri = UriComponentsBuilder
                .fromHttpUrl(rococoUserdataBaseUri + "/api/userdata/currentUser")
                .queryParams(params)
                .build()
                .toUri();

        return webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(UserJson.class)
                .block();
    }

    @Nonnull
    @Override
    public UserJson updateUser(@Nonnull UserJson userJson) {
        URI uri = UriComponentsBuilder
                .fromHttpUrl(rococoUserdataBaseUri + "/api/userdata/updateUser")
                .build()
                .toUri();

        return webClient.patch()
                .uri(uri)
                .bodyValue(userJson)
                .retrieve()
                .bodyToMono(UserJson.class)
                .block();
    }

}
