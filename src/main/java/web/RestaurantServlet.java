package web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.Util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RestaurantServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(RestaurantServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("redirect to restaurants.jsp");
        req.setAttribute("restaurants", Util.getRests());
        req.getRequestDispatcher("/restaurants.jsp").forward(req, resp);
    }
}
