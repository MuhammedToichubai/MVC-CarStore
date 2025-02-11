package java16.services.impl;

import jakarta.annotation.PostConstruct;
import java16.models.User;
import java16.repositories.UserRepo;
import java16.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceDefault implements UserService {
    private final UserRepo userRepository;

    @Override
    public String save(User user, boolean isNew) {
      Boolean exist =  userRepository.existEmail(user.getEmail());
      if (exist && isNew) {
       log.error("User with email {} already exists", user.getEmail());
       return "User with email " + user.getEmail() + " already exists";
      }else {
          user.setRole("USER");
          userRepository.save(user);
          return "User with email " + user.getEmail() + " saved";
      }

    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long userId) {
       return userRepository.findById(userId);
    }

    @PostConstruct
    public void init() {
        Boolean existEmail = userRepository.existEmail("guljamal.dev@gmail.com");
        if (!existEmail) {
            List<User> users = new ArrayList<>(Arrays.asList(
                    new User("guljamal.dev@gmail.com", "gPassword", "+996777777777", "ADMIN", "Guljamal Zhanybekova"),
                    new User("ainura.dev@gmail.com", "aPassword", "+996222222222", "USER", "Ainura Nusubalieva"),
                    new User("kanchoro.dev@gmail.com", "kPassword", "+996500444171", "USER", "Kanchor Mamaraiymov"),
                    new User("kanbolot.dev@gmail.com", "kPassword", "+996500005005", "USER", "Kanbolot Kalbaev")
            ));
            users.forEach(userRepository::save);
        }
    }
}
