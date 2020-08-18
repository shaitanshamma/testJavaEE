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
public class BrandRepository {

    private static final Logger logger = LoggerFactory.getLogger(BrandRepository.class);

    @Inject
    private ServletContext context;

    private Connection conn;


    public BrandRepository() {
    }

    @PostConstruct
    public void init() throws SQLException {
        conn = (Connection) context.getAttribute("connection");
        createTableIfNotExists(conn);
    }
    public void insert(Brand brand) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "insert into brands(title, country) values (?, ?);")) {
            stmt.setString(1, brand.getTitle());
            stmt.setString(2, brand.getCountry());
            stmt.execute();
        }
    }

    public void update(Brand brand) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "update brands set title = ?, country = ? where id = ?;")) {
            stmt.setString(1, brand.getTitle());
            stmt.setString(2, brand.getCountry());
            stmt.setLong(3, brand.getId());
            stmt.execute();
        }
    }

    public void delete(long id) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "delete from brands where id = ?;")) {
            stmt.setLong(1, id);
            stmt.execute();
        }
    }

    public Optional<Brand> findById(long id) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "select id, title, country from brands where id = ?")) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(new Brand(rs.getLong(1), rs.getString(2), rs.getString(3)));
            }
        }
        return Optional.empty();
    }

    public List<Brand> findAll() throws SQLException {
        List<Brand> res = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("select id, title, country from brands");

            while (rs.next()) {
                res.add(new Brand(rs.getLong(1), rs.getString(2), rs.getString(3)));
            }
        }
        return res;
    }

    private void createTableIfNotExists(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("create table if not exists brands (\n" +
                    "    id int auto_increment primary key,\n" +
                    "    title varchar(255),\n" +
                    "    country varchar(255));");
        }
    }
}
