package ru.shamma;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

public class MyFirstServlet extends HttpServlet {
private transient ServletConfig servletConfig;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        this.servletConfig = servletConfig;
    }

    @Override
    public ServletConfig getServletConfig() {
        return this.servletConfig;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/header").include(servletRequest,servletResponse);
        getServletContext().getRequestDispatcher("/navbar").include(servletRequest,servletResponse);
        servletResponse.getWriter().println("<h1>Основной контент</h1>");
        servletResponse.getWriter().println("<a href = 1>Левая ссылка для проверки</a>");
        getServletContext().getRequestDispatcher("/footer").include(servletRequest,servletResponse);
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
