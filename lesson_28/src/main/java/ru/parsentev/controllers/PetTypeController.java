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
import ru.parsentev.models.PetType;
import ru.parsentev.models.User;
import ru.parsentev.repositories.PetTypeRepository;
import ru.parsentev.repositories.UserRepository;

/**
 * @author parsentev
 * @since 22.06.2016
 */
@Controller
@RequestMapping(value = "/pets")
public class PetTypeController {
    private static final Logger log = LoggerFactory.getLogger(PetTypeController.class);

    private final PetTypeRepository repository;

    @Autowired
    public PetTypeController(final PetTypeRepository repository) {
        this.repository = repository;
    }


    @RequestMapping(value = "/types", method = RequestMethod.GET)
    public String petsView(ModelMap model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("types", Lists.newArrayList(this.repository.findAll()));
        return "pets/types";
    }

    @RequestMapping(value = "/types/add", method = RequestMethod.POST)
    public String save(@ModelAttribute PetType type) {
        this.repository.save(type);
        return "redirect:/pets/types.do";
    }
}
