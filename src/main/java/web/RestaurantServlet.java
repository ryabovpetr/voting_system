package web;

import com.sun.net.httpserver.Request;
import model.Restaurant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.RestaurantInMemoryRepository;
import repository.RestaurantRepository;
import utils.Util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Objects;

public class RestaurantServlet extends HttpServlet {
    private static RestaurantRepository repository;

    @Override
    public void init() throws ServletException {
        super.init();
        repository = new RestaurantInMemoryRepository();
    }

    private static final Logger log = LoggerFactory.getLogger(RestaurantServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        switch (action == null ? "all" : action) {
            case "delete":
                int id = getId(req);
                log.info("Delete id={}", id);
                repository.delete(id);
                resp.sendRedirect("restaurants");
                break;
            case "create":
            case "update":
                Restaurant restaurant = action.equals("create") ? new Restaurant("") : repository.get(getId(req));
                req.setAttribute("restaurant", restaurant);
                req.getRequestDispatcher("/restaurantForm.jsp").forward(req, resp);
                break;
            case "all":
            default:
                log.info("getAll");
                req.setAttribute("restaurants", repository.getAll());
                req.getRequestDispatcher("/restaurants.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");

        Restaurant restaurant = new Restaurant(id.isEmpty() ? null : Integer.valueOf(id),
                req.getParameter("name"), new HashMap<>(),
                Integer.parseInt(req.getParameter("countVotes")));

        log.info(restaurant.isNew() ? "Create {}" : "Update {}", restaurant);
        repository.save(restaurant);
        resp.sendRedirect("restaurants");
    }

    private int getId (HttpServletRequest req) {
        String id = Objects.requireNonNull((req.getParameter("id")));
        return Integer.parseInt(id);
    }
}
