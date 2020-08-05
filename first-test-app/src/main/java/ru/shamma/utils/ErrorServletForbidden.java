package ru.shamma.utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "ErrorServletForbidden", urlPatterns = "/errorForbidden")
public class ErrorServletForbidden extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("<h1 style=\"color: red\">АЛЯРМА!ДОСТУП ЗАПРЕЩЕН! 403</h1>");
    }
}
