package com.application.services.user;

import com.application.exception.UserNotFoundException;
import com.application.model.User;
import com.application.repository.UsersRepository;
import com.application.utils.CryptPassword;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UsersRepository usersRepository;

  @Transactional(readOnly = true)
  public User findUserById(int id) {
    return usersRepository.findById(id)
        .orElseThrow(UserNotFoundException::new);
  }

  @Transactional(readOnly = true)
  public List<User> findAll(Sort sort) {
    return usersRepository.findAll(sort);
  }

  @Transactional
  public User createUser(String username, String name, String email, String password) {
    User user = new User();
    user.setName(name);
    user.setUsername(username);
    user.setEmail(email);
    user.setPassword(CryptPassword.cryptPassword(password));

    return usersRepository.save(user);
  }

  @Transactional
  public void updateUser(int id, Optional<String> username, Optional<String> name, Optional<String> email) {
    User user = findUserById(id);

    name.ifPresent(user::setName);
    username.ifPresent(user::setUsername);
    email.ifPresent(user::setEmail);

    usersRepository.save(user);
  }

  @Transactional
  public void updateUserPassword(int id, String password) {
    User user = findUserById(id);
    user.setPassword(CryptPassword.cryptPassword(password));

    usersRepository.save(user);
  }
}
