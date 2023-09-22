package br.com.commerce.api.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.commerce.api.dto.User.UserMapper;
import br.com.commerce.api.dto.User.UserRequest;
import br.com.commerce.api.dto.User.UserResponse;
import br.com.commerce.api.models.User;
import br.com.commerce.api.repositories.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class UserService {

  @Autowired
  UserRepository userRepository;

  @Autowired
  UserMapper userMapper;

  public List<UserResponse> findAllUsers() {
    return userMapper.toListUserResponse(userRepository.findAll());
  }

  public UserResponse findById(UUID id) {
    return userMapper.toUserResponse(userRepository.findById(id));
  }

  public UserResponse findById(String id) {
    return this.findById(UUID.fromString(id));
  }

  public User findByUserId(String id) {
    return userMapper.toUser(this.findById(UUID.fromString(id)));
  }

  @Transactional
  public UserResponse save(UserRequest user2) {
    User user = userMapper.toUser(user2);
    return userMapper.toUserResponse(userRepository.save(user));
  }

  @Transactional
  public UserResponse update(UserRequest user, UUID id) {
    User userUpdate = userMapper.toUser(user);
    if (userRepository.findById(id).isPresent()) {
      userUpdate.setId(id);
      return userMapper.toUserResponse(userRepository.save(userUpdate));
    }
    return null;
  }

  @Transactional
  public void delete(UUID id) {
    if(userRepository.findById(id).isPresent()){
      userRepository.deleteById(id);
    }
  }

}
