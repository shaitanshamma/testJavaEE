package ru.shamma;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.shamma.persist.Product;
import ru.shamma.persist.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Optional;

@WebServlet(name = "CartControllerServlet", urlPatterns ="/cart")
public class CartControllerServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(CartControllerServlet.class);

    private ProductRepository productRepository;

    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Cart page");
        getServletContext().getRequestDispatcher("/WEB-INF/cart.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
