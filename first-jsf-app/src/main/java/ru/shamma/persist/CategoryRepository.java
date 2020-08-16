package ru.shamma.persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Named
public class CategoryRepository {

    private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);

    private Connection conn;

    public CategoryRepository(){
    }

    @Inject
    private ServletContext context;

    @PostConstruct
    public void init() throws SQLException {
        conn = (Connection) context.getAttribute("connection");
        createTableIfNotExists(conn);
    }

    public void insert(Category category) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "insert into categories (title) values (?);")) {
            stmt.setString(1, category.getTitle());
            stmt.execute();
        }
    }

    public void update(Category category) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "update categories set title = ? where id = ?;")) {
            stmt.setString(1, category.getTitle());
            stmt.setLong(2, category.getId());
            stmt.execute();
        }
    }

    public void delete(long id) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "delete from categories where id = ?;")) {
            stmt.setLong(1, id);
            stmt.execute();
        }
    }

    public Optional<Category> findById(long id) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "select id, title from categories where id = ?")) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(new Category(rs.getLong(1), rs.getString(2)));
            }
        }
        return Optional.empty();
    }

    public List<Category> findAll() throws SQLException {
        List<Category> res = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("select id, title from categories");

            while (rs.next()) {
                res.add(new Category(rs.getLong(1), rs.getString(2)));
            }
        }
        return res;
    }

    private void createTableIfNotExists(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("create table if not exists categories (\n" +
                    "    id int auto_increment primary key,\n" +
                    "    title varchar(255) \n" +
                    ");");
        }
    }
}
