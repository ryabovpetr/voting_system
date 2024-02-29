package rpg.web;

import rpg.model.Restaurant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rpg.repository.RestaurantInMemoryRepository;
import rpg.repository.RestaurantRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
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
                Restaurant restaurant = action.equals("create") ? new Restaurant() : repository.get(getId(req));
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
        Restaurant restaurant = new Restaurant();
        if (!id.isEmpty()) {
            restaurant.setId(Integer.parseInt(id));
            restaurant.setCountVotes(Integer.parseInt(req.getParameter("countVotes")));
        }
        if (!restaurant.isNew()) {
            restaurant = repository.get(restaurant.getId());
            String[] dishNames = req.getParameterValues("editDishName");
            String[] dishPrices = req.getParameterValues("editDishPrice");
            String[] dishKeys = req.getParameterValues("editDishKey");

            Map<String, Integer> dishMenu = restaurant.getLunchMenu();

            if (!dishMenu.isEmpty()) {
                for (int i = 0; i < dishNames.length; i++) {
                    String dishName = dishNames[i];
                    String stringDishPrice = dishPrices[i];
                    String oldDishKey = dishKeys[i];

                    dishMenu.remove(oldDishKey);
                    if (!stringDishPrice.isEmpty() && !dishName.isEmpty())
                        dishMenu.put(dishName, Integer.parseInt(stringDishPrice));
                }
            }
        }

        for (int i = 1; i < 4; i++) {
            String newDishName = req.getParameter("newDishName" + i);
            if (!newDishName.isEmpty()) {
                String stringNewPrice = req.getParameter("newDishPrice" + i);
                if (!stringNewPrice.isEmpty()) {
                    int newDishPrice = Integer.parseInt(stringNewPrice);
                    restaurant.getLunchMenu().put(newDishName, newDishPrice);
                }
            }
        }

        String name = req.getParameter("name");
        if (!name.isEmpty())
            restaurant.setName(name);

        log.info(restaurant.isNew() ? "Create {}" : "Update {}", restaurant);
        repository.save(restaurant);
        resp.sendRedirect("restaurants");
    }

    private int getId(HttpServletRequest req) {
        String id = Objects.requireNonNull((req.getParameter("id")));
        return Integer.parseInt(id);
    }
}
