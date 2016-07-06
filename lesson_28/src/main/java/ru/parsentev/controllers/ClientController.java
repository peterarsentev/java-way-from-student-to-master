package ru.parsentev.controllers;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.parsentev.models.Message;
import ru.parsentev.models.User;
import ru.parsentev.repositories.MessageRepository;
import ru.parsentev.repositories.PetRepository;
import ru.parsentev.repositories.RoleRepository;
import ru.parsentev.repositories.UserRepository;
import ru.parsentev.services.Service;

import java.sql.Timestamp;

/**
 * @author parsentev
 * @since 22.06.2016
 */
@Controller
public class ClientController {
    private static final Logger log = LoggerFactory.getLogger(ClientController.class);

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final MessageRepository messageRepository;
    private final PetRepository petRepository;
    private final Service service;

    @Autowired
    public ClientController(final UserRepository userRepository, final RoleRepository roleRepository,
                            final MessageRepository messageRepository, final PetRepository petRepository,
                            final Service service) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.messageRepository = messageRepository;
        this.petRepository = petRepository;
        this.service = service;
    }

    @RequestMapping(value = "/client", method = RequestMethod.GET)
    public String clientView(ModelMap model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = this.userRepository.findByUsername(((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername());
        model.addAttribute("user", user);
        model.addAttribute("messages", this.messageRepository.findByOwner(user));
        return "clients/client";
    }

    @RequestMapping(value = "/client/message/add", method = RequestMethod.POST)
    public String save(@ModelAttribute Message message) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        message.setAuthor(this.userRepository.findByUsername(((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername()));
        message.setCreated(new Timestamp(System.currentTimeMillis()));
        this.messageRepository.save(message);
        return "redirect:/client.do";
    }
}
