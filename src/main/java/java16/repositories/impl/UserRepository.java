package java16.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java16.models.User;
import java16.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class UserRepository implements UserRepo {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    @Transactional
    public void save(User user) {
        if (user.getId() == null) {
            entityManager.persist(user);
        }else {
            entityManager.merge(user);
        }
    }

    @Override
    public List<User> findAll() {
        return entityManager
                .createQuery("select u from User u order by u.id", User.class)
                .getResultList();
    }

    @Override
    public Boolean existEmail(String email) {
      return  entityManager
                .createQuery("select count(u) > 0 from User u where u.email = :email",
                        Boolean.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    @Override
    public User findById(Long userId) {
        return entityManager
        .createQuery("select u from User u where u.id = :id", User.class)
                .setParameter("id", userId)
                .getSingleResult();
    }
}
