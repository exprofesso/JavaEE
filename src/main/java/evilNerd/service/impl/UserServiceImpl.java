package evilNerd.service.impl;

import evilNerd.domain.User;
import evilNerd.repository.UserRepository;
import evilNerd.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
//@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

 //   private final UserRepository userRepository;

    //1. Validation layer
    //2. Convert http request params into User object
    //3. Extended calls into DB or external services

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }



    @Override
    public User findById(Long userId) {
        return userRepository.findById(userId);
    }

    public List<User> search (String query){
      return  userRepository.search(query);
    }

    @Override
    public Optional<User> findOne(Long key) { return userRepository.findOne(key); }

    @Override
    public User update(User user) { return userRepository.update(user); }

    @Override
    public int delete(User user) {return 0; }




}
