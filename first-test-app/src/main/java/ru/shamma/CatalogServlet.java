package ru.shamma;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "ProductServlet", urlPatterns = "/first-servlet/catalog")
public class CatalogServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/header").include(req,resp);
        getServletContext().getRequestDispatcher("/navbar").include(req,resp);
        resp.getWriter().println("<h1>Каталог, ссылки, фотки, краткое описание</h1>");
        getServletContext().getRequestDispatcher("/footer").include(req,resp);
    }
}
