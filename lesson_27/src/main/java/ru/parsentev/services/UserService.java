package ru.parsentev.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.parsentev.models.User;
import ru.parsentev.repositories.MessageRepository;
import ru.parsentev.repositories.PetRepository;
import ru.parsentev.repositories.UserRepository;

/**
 * TODO: comment
 *
 * @author parsentev
 * @since 06.07.2016
 */
@Component
public class UserService implements Service {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final PetRepository petRepository;
    private final MessageRepository messageRepository;

    @Autowired
    public UserService(UserRepository userRepository, PetRepository petRepository, MessageRepository messageRepository) {
        this.userRepository = userRepository;
        this.petRepository = petRepository;
        this.messageRepository = messageRepository;
    }

    @Transactional
    @Override
    public void deleteUser(int id) {
        User user = new User(id);
        this.messageRepository.deleteByOwner(new User(id));
        this.petRepository.deleteByOwner(user);
        this.userRepository.delete(user);
    }
}
