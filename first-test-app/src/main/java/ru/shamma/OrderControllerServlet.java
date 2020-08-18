package ru.shamma;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.shamma.persist.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "OrderControllerServlet", urlPatterns ="/order")
public class OrderControllerServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(OrderControllerServlet.class);

    private ProductRepository productRepository;

    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Order page");
        getServletContext().getRequestDispatcher("/WEB-INF/order.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
