package ru.shamma;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.shamma.persist.Category;
import ru.shamma.persist.CategoryRepository;
import ru.shamma.persist.Product;
import ru.shamma.persist.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

@WebServlet(name = "CategoryControllerServlet", urlPatterns = "/categories/*")
public class CategoryControllerServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(CategoryControllerServlet.class);

    private CategoryRepository categoryRepository;

    @Override
    public void init() throws ServletException {
       categoryRepository = (CategoryRepository) getServletContext().getAttribute("categoryRepository");
        if (categoryRepository == null) {
            throw new ServletException("Category repository not initialized");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Category page");
        logger.info(req.getPathInfo());
        if (req.getServletPath().equals("/categories")&&req.getPathInfo()==null) {
            try {
                req.setAttribute("categories", categoryRepository.findAll());
                getServletContext().getRequestDispatcher("/WEB-INF/categories.jsp").forward(req, resp);
            } catch (SQLException ex) {
                throw new IllegalStateException(ex);
            }
        } else if (req.getServletPath().equals("/categories")&&req.getPathInfo().equals("/new")) {
            req.setAttribute("category", new Category());
            getServletContext().getRequestDispatcher("/WEB-INF/category.jsp").forward(req, resp);
        } else if (req.getServletPath().equals("/categories")&&req.getPathInfo().equals("/edit")) {
            String id = req.getParameter("id");
            try {
                Optional<Category> opt = categoryRepository.findById(Long.parseLong(id));
                if (opt.isPresent()) {
                    req.setAttribute("category", opt.get());
                } else {
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                    return;
                }
            } catch (SQLException ex) {
                throw new IllegalStateException(ex);
            }
            getServletContext().getRequestDispatcher("/WEB-INF/category.jsp").forward(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getServletPath().equals("/categories")&&req.getPathInfo().equals("/upsert")) {
            try {
                String strId = req.getParameter("id");
                if (strId.isEmpty()) {
                    categoryRepository.insert(new Category(-1L,
                            req.getParameter("title")));
                } else {
                    categoryRepository.update(new Category(Long.parseLong(strId),
                            req.getParameter("title")));
                }
                resp.sendRedirect(getServletContext().getContextPath().concat(req.getServletPath()));
            } catch (SQLException ex) {
                throw new IllegalStateException(ex);
            }
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
