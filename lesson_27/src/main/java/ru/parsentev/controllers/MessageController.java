package ru.parsentev.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.parsentev.models.Message;
import ru.parsentev.repositories.MessageRepository;
import ru.parsentev.repositories.UserRepository;

import java.sql.Timestamp;

/**
 * @author parsentev
 * @since 22.06.2016
 */
@Controller
public class MessageController {
    private static final Logger log = LoggerFactory.getLogger(MessageController.class);

    private final MessageRepository repository;
    private final UserRepository userRepository;

    @Autowired
    public MessageController(final MessageRepository repository, final UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/messages/add", method = RequestMethod.POST)
    public String save(@ModelAttribute Message message) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        message.setAuthor(this.userRepository.findByUsername(((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername()));
        message.setCreated(new Timestamp(System.currentTimeMillis()));
        this.repository.save(message);
        return String.format("redirect:/user.do?id=%s", message.getOwner().getId());
    }

    @RequestMapping(value = "/messages/delete", method = RequestMethod.GET)
    public String delete(@RequestParam int userId, @RequestParam int messageId) {
        this.repository.delete(messageId);
        return String.format("redirect:/user.do?id=%s", userId);
    }
}
