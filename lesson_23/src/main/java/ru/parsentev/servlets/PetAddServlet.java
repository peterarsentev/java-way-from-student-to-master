package ru.parsentev.servlets;

import org.slf4j.Logger;
import ru.parsentev.models.Pet;
import ru.parsentev.storages.PetStorage;
import ru.parsentev.storages.PetTypeStorage;
import ru.parsentev.storages.UserStorage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * TODO: comment
 *
 * @author parsentev
 * @since 11.07.2016
 */
public class PetAddServlet extends HttpServlet {
    private static final Logger log = getLogger(PetAddServlet.class);
    private final PetTypeStorage types = PetTypeStorage.getInstance();
    private final UserStorage users = UserStorage.getInstance();
    private final PetStorage pets = PetStorage.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Pet pet = new Pet();
        pet.setNick(req.getParameter("nick"));
        pet.setType(this.types.findById(Integer.valueOf(req.getParameter("type.id"))).get());
        pet.setOwner(this.users.findById(Integer.valueOf(req.getParameter("owner.id"))).get());
        this.pets.add(pet);
        resp.sendRedirect(String.format("%s/users.do", req.getContextPath()));
    }
}
