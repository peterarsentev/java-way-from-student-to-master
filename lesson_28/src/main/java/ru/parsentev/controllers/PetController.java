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
import ru.parsentev.models.Pet;
import ru.parsentev.models.Role;
import ru.parsentev.repositories.PetRepository;
import ru.parsentev.repositories.PetTypeRepository;
import ru.parsentev.repositories.UserRepository;

/**
 * @author parsentev
 * @since 22.06.2016
 */
@Controller
public class PetController {
    private static final Logger log = LoggerFactory.getLogger(PetController.class);

    private final PetRepository repository;
    private final PetTypeRepository petTypeRepository;

    @Autowired
    public PetController(final PetRepository repository, final PetTypeRepository petTypeRepository) {
        this.repository = repository;
        this.petTypeRepository = petTypeRepository;
    }

    @RequestMapping(value = "/pets", method = RequestMethod.GET)
    public String petsAdd(@RequestParam int id, ModelMap model) {
        model.addAttribute("types", Lists.newArrayList(this.petTypeRepository.findAll()));
        model.addAttribute("user_id", id);
        return "pets/add";
    }

    @RequestMapping(value = "/pets/add", method = RequestMethod.POST)
    public String save(@ModelAttribute Pet pet) {
        this.repository.save(pet);
        return String.format("redirect:/user.do?id=%s", pet.getOwner().getId());
    }
}
