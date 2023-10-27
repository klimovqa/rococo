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

    private final UserRepository repository;

    @Autowired
    public UserDataService(UserRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(topics = "users", groupId = "userdata")
    public void listener(@Payload UserJson user, ConsumerRecord<String, UserJson> cr) {
        log.info("### Kafka topic [users] received message: " + user.getUsername());
        log.info("### Kafka consumer record: " + cr.toString());
        UserEntity userDataEntity = new UserEntity();
        userDataEntity.setUsername(user.getUsername());
        UserEntity userEntity = repository.save(userDataEntity);
        log.info(String.format(
                "### User '%s' successfully saved to database with id: %s",
                user.getUsername(),
                userEntity.getId()
        ));
    }


    public @Nonnull
    UserEntity findByUsername(@Nonnull String username) {
        return repository.findByUsername(username)
                .orElseThrow(() ->
                        new NotFoundException("Can`t find user by username: " + username));
    }

    public @Nonnull
    UserEntity update(@Nonnull UserJson user) {
        UserEntity currentUser = findByUsername(user.getUsername());
        currentUser.setFirstname(user.getFirstname());
        currentUser.setLastname(user.getLastname());
        currentUser.setAvatar(user.getAvatar());
        return repository.save(currentUser);
    }
}