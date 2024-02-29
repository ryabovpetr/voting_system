package rpg.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rpg.model.Restaurant;
import rpg.model.User;
import rpg.repository.RestaurantInMemoryRepository;
import rpg.repository.RestaurantRepository;
import rpg.repository.UserInMemoryRepository;
import rpg.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

public class UserServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(UserServlet.class);

    private static UserRepository repository;

    @Override
    public void init() throws ServletException {
        super.init();
        repository = new UserInMemoryRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        switch (action == null ? "all" : action) {
            case "delete":
                int id = getId(req);
                log.info("Delete id={}", id);
                repository.delete(id);
                resp.sendRedirect("users");
                break;
            case "create":
            case "update":
                User user = action.equals("create") ? new User() : repository.get(getId(req));
                req.setAttribute("user", user);
                req.getRequestDispatcher("/userForm.jsp").forward(req, resp);
                break;
            case "all":
            default:
                log.info("getAll");
                req.setAttribute("users", repository.getAll());
                req.getRequestDispatcher("/users.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");
        User user = new User();
        if (!id.isEmpty())
            user.setId(Integer.parseInt(id));

        if (!user.isNew())
            user = repository.get(user.getId());

        String email = req.getParameter("email");
        if (!email.isEmpty())
            user.setEmail(email);

        log.info(user.isNew() ? "Create {}" : "Update {}", user);
        repository.save(user);
        resp.sendRedirect("users");
    }

    private int getId(HttpServletRequest req) {
        String id = Objects.requireNonNull((req.getParameter("id")));
        return Integer.parseInt(id);
    }
}
