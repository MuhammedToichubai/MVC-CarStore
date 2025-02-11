package java16.repositories;

import java16.models.User;

import java.util.List;

public interface UserRepo {
    void save(User user);

    List<User> findAll();

    Boolean existEmail(String email);

    User findById(Long userId);
}
