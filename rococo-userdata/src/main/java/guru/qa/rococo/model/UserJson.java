package guru.qa.rococo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import guru.qa.rococo.data.UserEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;


@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class UserJson {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("username")
    private String username;
    @JsonProperty("firstname")
    private String firstname;
    @JsonProperty("lastname")
    private String lastname;
    @JsonProperty("avatar")
    private String avatar;

    public static UserJson toUserJson(UserEntity entity) {
        UserJson userJson = new UserJson();
        userJson.setId(entity.getId());
        userJson.setUsername(entity.getUsername());
        userJson.setFirstname(entity.getFirstname());
        userJson.setLastname(entity.getLastname());
        userJson.setAvatar(entity.getAvatar());
        return userJson;
    }
}
