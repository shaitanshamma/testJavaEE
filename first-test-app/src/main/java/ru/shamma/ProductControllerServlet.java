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

@WebServlet(name = "ProductControllerServlet", urlPatterns = {"", "/"})
public class ProductControllerServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(ProductControllerServlet.class);

    private ProductRepository productRepository;

    @Override
    public void init() throws ServletException {
        productRepository = (ProductRepository) getServletContext().getAttribute("productRepository");
        if (productRepository == null) {
            throw new ServletException("Product repository not initialized");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Index product page");

        if (req.getServletPath().equals("/")) {
            try {
                req.setAttribute("products", productRepository.findAll());
                getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
            } catch (SQLException ex) {
                throw new IllegalStateException(ex);
            }
        } else if (req.getServletPath().equals("/new")) {
            req.setAttribute("product", new Product());
            getServletContext().getRequestDispatcher("/WEB-INF/product.jsp").forward(req, resp);
        } else if (req.getServletPath().equals("/edit")) {
            String id = req.getParameter("id");
            try {
                Optional<Product> opt = productRepository.findById(Long.parseLong(id));
                if (opt.isPresent()) {
                    req.setAttribute("product", opt.get());
                } else {
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                    return;
                }
            } catch (SQLException ex) {
                throw new IllegalStateException(ex);
            }
            getServletContext().getRequestDispatcher("/WEB-INF/product.jsp").forward(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getServletPath().equals("/upsert")) {
            try {
                String strId = req.getParameter("id");
                if (strId.isEmpty()) {
                    productRepository.insert(new Product(-1L,
                            req.getParameter("name"),
                            req.getParameter("description"),
                            BigDecimal.valueOf(Long.parseLong(req.getParameter("price")))));
                } else {
                    productRepository.update(new Product(Long.parseLong(strId),
                            req.getParameter("name"),
                            req.getParameter("description"),
                            BigDecimal.valueOf(Long.parseLong(req.getParameter("price")))));
                }
                resp.sendRedirect(getServletContext().getContextPath());
            } catch (SQLException ex) {
                throw new IllegalStateException(ex);
            }
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
