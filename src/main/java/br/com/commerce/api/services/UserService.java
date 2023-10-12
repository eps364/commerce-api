package br.com.commerce.api.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.commerce.api.dto.user.UserMapper;
import br.com.commerce.api.dto.user.UserRequest;
import br.com.commerce.api.dto.user.UserResponse;
import br.com.commerce.api.models.User;
import br.com.commerce.api.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class UserService  {
  private static final String CACHE_FIND_ALL = "UserService.findAllUsers";
  private static final String CACHE_FIND_BY_ID = "UserService.findById";
  private static final String CACHE_FIND_BY_USER = "UserService.findByUserId";

  @Autowired
  UserRepository userRepository;

  @Autowired
  UserMapper userMapper;

  @Cacheable(CACHE_FIND_ALL)
  public List<UserResponse> findAllUsers() {
    log.info(this.getClass().getName() + " | " + "findAllUsers");
    return userMapper.toListUserResponse(userRepository.findAll());
  }

  @Cacheable(CACHE_FIND_BY_ID)
  public UserResponse findById(UUID id) {
    log.info(this.getClass().getName() + " | " + "findById");
    return userMapper.toUserResponse(userRepository.findById(id));
  }

  @Cacheable(CACHE_FIND_BY_ID)
  public UserResponse findById(String id) {
    log.info(this.getClass().getName() + " | " + "findById");
    return this.findById(UUID.fromString(id));
  }

  @Cacheable()
  public User findByUserId(String id) {
    log.info(this.getClass().getName() + " | " + "findByUserId");
    return userMapper.toUser(this.findById(UUID.fromString(id)));
  }

  @Transactional
  @CacheEvict({ CACHE_FIND_BY_USER, CACHE_FIND_BY_ID, CACHE_FIND_ALL })
  public UserResponse save(UserRequest user2) {
    User user = userMapper.toUser(user2);
    return userMapper.toUserResponse(userRepository.save(user));
  }

  @Transactional
  @CachePut(CACHE_FIND_BY_ID)
  public UserResponse update(UserRequest user, UUID id) {
    User userUpdate = userMapper.toUser(user);
    if (userRepository.findById(id).isPresent()) {
      userUpdate.setId(id);
      return userMapper.toUserResponse(userRepository.save(userUpdate));
    }
    return null;
  }

  @Transactional
  @CachePut(CACHE_FIND_BY_ID)
  public void delete(UUID id) {
    if (userRepository.findById(id).isPresent()) {
      userRepository.deleteById(id);
    }
  }
}
