package guru.qa.rococo.service;
import guru.qa.rococo.data.UserEntity;
import guru.qa.rococo.data.repository.UserRepository;
import guru.qa.rococo.model.UserJson;
import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import guru.qa.rococo.ex.NotFoundException;

@Slf4j
@Component
public class UserDataService {

    private final UserRepository userRepository;

    @Autowired
    public UserDataService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @KafkaListener(topics = "users", groupId = "userdata")
    public void listener(@Payload UserJson user, ConsumerRecord<String, UserJson> cr) {
        log.info("### Kafka topic [users] received message: " + user.getUsername());
        log.info("### Kafka consumer record: " + cr.toString());
        UserEntity userDataEntity = new UserEntity();
        userDataEntity.setUsername(user.getUsername());
        UserEntity userEntity = userRepository.save(userDataEntity);
        log.info(String.format(
                "### User '%s' successfully saved to database with id: %s",
                user.getUsername(),
                userEntity.getId()
        ));
    }


    public @Nonnull
    UserEntity getCurrentUser(@Nonnull String username) {
        UserEntity user = userRepository.findByUsername(username);
        if (user == null) {
            throw new NotFoundException("Can`t find user by username: " + username);
        }
        return user;
    }

    public @Nonnull
    UserEntity updateUser(@Nonnull UserJson user) {
        UserEntity currentUser = getCurrentUser(user.getUsername());
        currentUser.setFirstname(user.getFirstname());
        currentUser.setLastname(user.getLastname());
        currentUser.setAvatar(user.getAvatar());
        return userRepository.save(currentUser);
    }
}