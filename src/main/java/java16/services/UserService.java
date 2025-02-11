package java16.services;

import java16.models.User;

import java.util.List;

public interface UserService {
    String save(User user, boolean isNew);

    List<User> findAll();

    User findById(Long userId);
}
