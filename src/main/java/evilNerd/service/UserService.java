package evilNerd.service;

import evilNerd.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    User save(User user);

    User update(User user);

    User findById(Long userId);

    List<User> search (String query);

    Optional<User> findOne(Long key);

    int delete (User user);




}
