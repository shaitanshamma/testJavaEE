package ru.shamma.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.shamma.dao.UserDao;
import ru.shamma.persist.User;
import ru.shamma.persist.UserRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @EJB
    private UserRepository userRepository;

    @TransactionAttribute
    public void merge(UserDao user) {
        User merged = userRepository.merge(new User(user));
        user.setId(merged.getId());
    }

    @TransactionAttribute
    public void delete(int id) {
        userRepository.delete(id);
    }

    @TransactionAttribute
    public UserDao findById(int id) {
        return new UserDao(userRepository.findById(id));
    }

    @TransactionAttribute
    public boolean existsById(int id) {
        return userRepository.findById(id) != null;
    }

    @TransactionAttribute
    public List<UserDao> getAllUsers() {
        return userRepository.getAllUsers().stream()
                .map(UserDao::new)
                .collect(Collectors.toList());
    }

}
