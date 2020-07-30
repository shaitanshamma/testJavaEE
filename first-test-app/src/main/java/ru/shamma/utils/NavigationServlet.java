package ru.shamma.utils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "HeaderServlet", urlPatterns = "/navbar")
public class NavigationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("<a href = /first-test-app/first-servlet> Main </a>");
        resp.getWriter().println("<a href = /first-test-app/first-servlet/catalog> Catalog </a>");
        resp.getWriter().println("<a href = /first-test-app/first-servlet/product> Product </a>");
        resp.getWriter().println("<a href = /first-test-app/first-servlet/cart> Cart </a>");
        resp.getWriter().println("<a href = /first-test-app/first-servlet/order> Order </a>");
    }
}
