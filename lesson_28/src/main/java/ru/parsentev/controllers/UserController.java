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
import ru.parsentev.models.User;
import ru.parsentev.repositories.MessageRepository;
import ru.parsentev.repositories.PetRepository;
import ru.parsentev.repositories.RoleRepository;
import ru.parsentev.repositories.UserRepository;
import ru.parsentev.services.Service;
import ru.parsentev.services.UserService;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author parsentev
 * @since 22.06.2016
 */
@Controller
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final MessageRepository messageRepository;
    private final PetRepository petRepository;
    private final Service service;

    @Autowired
    public UserController(final UserRepository userRepository, final RoleRepository roleRepository,
                          final MessageRepository messageRepository, final PetRepository petRepository,
                          final Service service) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.messageRepository = messageRepository;
        this.petRepository = petRepository;
        this.service = service;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String usersView(ModelMap model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("users", Lists.newArrayList(this.userRepository.findAll()));
        model.addAttribute("roles", Lists.newArrayList(this.roleRepository.findAll()));
        return "users/users";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String userView(@RequestParam int id, ModelMap model) {
        model.addAttribute("user", this.userRepository.findOne(id));
        model.addAttribute("messages", this.messageRepository.findByOwner(new User(id)));
        return "users/user";
    }

    @RequestMapping(value = "/users/add", method = RequestMethod.GET)
    public String addView(ModelMap model) {
        model.addAttribute("roles", Lists.newArrayList(this.roleRepository.findAll()));
        return "users/add";
    }

    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    public String save(@ModelAttribute User user) {
        this.userRepository.save(user);
        return "redirect:/users.do";
    }

    @RequestMapping(value = "/users/edit", method = RequestMethod.GET)
    public String editView(@RequestParam int id, ModelMap model) {
        model.addAttribute("user", this.userRepository.findOne(id));
        model.addAttribute("roles", Lists.newArrayList(this.roleRepository.findAll()));
        return "users/edit";
    }

    @RequestMapping(value = "/users/edit", method = RequestMethod.POST)
    public String edit(@ModelAttribute User user) {
        user.setPets(this.petRepository.findByOwner(new User(user.getId())));
        this.userRepository.save(user);
        return "redirect:/users.do";
    }

    @RequestMapping(value = "/users/delete", method = RequestMethod.GET)
    public String delete(@RequestParam int id) {
        this.service.deleteUser(id);
        return "redirect:/users.do";
    }
}
