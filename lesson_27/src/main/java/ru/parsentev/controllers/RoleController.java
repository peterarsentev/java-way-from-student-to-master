package ru.parsentev.controllers;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.parsentev.models.Role;
import ru.parsentev.repositories.RoleRepository;

/**
 * @author parsentev
 * @since 22.06.2016
 */
@Controller
public class RoleController {
    private static final Logger log = LoggerFactory.getLogger(RoleController.class);

    private final RoleRepository repository;

    @Autowired
    public RoleController(final RoleRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/roles", method = RequestMethod.GET)
    public String showItems(ModelMap model) {
        model.addAttribute("roles", Lists.newArrayList(this.repository.findAll()));
        return "roles/roles";
    }

    @RequestMapping(value = "/roles/add", method = RequestMethod.POST)
    public String save(@ModelAttribute Role user) {
        this.repository.save(user);
        return "redirect:/roles.do";
    }
}
