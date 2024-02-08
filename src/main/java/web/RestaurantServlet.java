package web;

import model.Restaurant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.RestaurantInMemoryRepository;
import repository.RestaurantRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
        String action = req.getParameter("action");
        String id = req.getParameter("id");
        Restaurant restaurant = new Restaurant();
        if (!id.isEmpty()) {
            restaurant.setId(Integer.parseInt(id));
            restaurant.setCountVotes(Integer.parseInt(req.getParameter("countVotes")));
        }

        if (!restaurant.isNew()) restaurant = repository.get(restaurant.getId());
        if (action != null) {
            if (action.startsWith("editDish:")) {
                int index = Integer.parseInt(action.substring(9));
                String dishName = req.getParameter("editDishKey" + index);
                String newDishName = req.getParameter("editDishName" + index);
                int newDishPrice = Integer.parseInt(req.getParameter("editDishPrice" + index));
                restaurant.getLunchMenu().remove(dishName);
                restaurant.getLunchMenu().put(newDishName, newDishPrice);
            } else if (action.startsWith("deleteDish:")) {
                int index = Integer.parseInt(action.substring(11));
                String dishName = req.getParameter("editDishKey" + index);
                restaurant.getLunchMenu().remove(dishName);
            } else if (action.equals("addDish")) {
                String newDishName = req.getParameter("newDishName");
                int newDishPrice = Integer.parseInt(req.getParameter("newDishPrice"));
                restaurant.getLunchMenu().put(newDishName, newDishPrice);
            }
        } else {
            String newDishName = req.getParameter("newDishName");
            if (!newDishName.isEmpty()) {
            int newDishPrice = Integer.parseInt(req.getParameter("newDishPrice"));
            restaurant.getLunchMenu().put(newDishName, newDishPrice);
            }
        }

        String name = req.getParameter("name");
        if (!name.isEmpty()) restaurant.setName(name);

        log.info(restaurant.isNew() ? "Create {}" : "Update {}", restaurant);
        repository.save(restaurant);
        resp.sendRedirect("restaurants");

//        Map<String, Integer> lunchMenu = new HashMap<>();
//        Map<String, String[]> map = new HashMap<>(req.getParameterMap());
//        String name = map.remove("name")[0];
//        String id = map.remove("id")[0];
//        String countVotes = map.remove("countVotes")[0];
//        map.forEach((s, strings) -> lunchMenu.put(s, Integer.parseInt(strings[0])));
    }

    private int getId (HttpServletRequest req) {
        String id = Objects.requireNonNull((req.getParameter("id")));
        return Integer.parseInt(id);
    }
}
