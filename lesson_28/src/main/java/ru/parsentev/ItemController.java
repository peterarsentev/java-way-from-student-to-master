package ru.parsentev;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.parsentev.models.Item;
import ru.parsentev.models.User;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author parsentev
 * @since 22.06.2016
 */
@Controller
public class ItemController {
    private static final Logger log = LoggerFactory.getLogger(ItemController.class);

    final List<Item> items = new CopyOnWriteArrayList<>();

    @RequestMapping(value = "/items", method = RequestMethod.GET)
    public String showItems(ModelMap model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user", new User(auth.getName()));
        model.addAttribute("items", items);
        return "index";
    }

    @RequestMapping(value = "/items", method = RequestMethod.POST)
    public String addItem(@ModelAttribute Item item) {
        this.items.add(item);
        return "redirect:items.do";
    }
}
