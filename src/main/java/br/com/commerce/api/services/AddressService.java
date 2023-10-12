package br.com.commerce.api.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.commerce.api.dto.user.UserMapper;
import br.com.commerce.api.dto.user.UserResponse;
import br.com.commerce.api.dto.user.address.AddressMapper;
import br.com.commerce.api.dto.user.address.AddressRequest;
import br.com.commerce.api.dto.user.address.AddressResponse;
import br.com.commerce.api.models.Address;
import br.com.commerce.api.models.User;
import br.com.commerce.api.repositories.AddressRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class AddressService {
  private static final String CACHE_FIND_ALL = "AddressService.findAllAddresss";
  private static final String CACHE_FIND_BY_ID = "AddressService.findById";

  @Autowired
  AddressRepository addressRepository;

  @Autowired
  AddressMapper addressMapper;

  @Autowired
  UserService userService;

  @Autowired
  UserMapper userMapper;

  @Cacheable(CACHE_FIND_ALL)
  public List<AddressResponse> findAllAddresss() {
    log.info(this.getClass().getName() + " | " + "findAllAddresss");
    return addressMapper.toListAddressResponse(addressRepository.findAll());
  }

  @Cacheable(CACHE_FIND_BY_ID)
  public AddressResponse findById(Long id) {
    log.info(this.getClass().getName() + " | " + "findById");
    return addressMapper.toAddressResponse(addressRepository.findById(id));
  }

  @Transactional
  @CacheEvict(CACHE_FIND_ALL)
  public AddressResponse save(AddressRequest address) {
    UserResponse userResponse = userService.findById(UUID.fromString(address.getUser()));
    if (userResponse != null) {
      User user = userMapper.toUser(userResponse);
      Address addressSave = addressMapper.toAddress(address);
      addressSave.setUser(user);
      return addressMapper.toAddressResponse(addressRepository.save(addressSave));
    }
    return null;

  }

  @Transactional
  @CachePut(CACHE_FIND_BY_ID)
  public AddressResponse update(AddressRequest address, Long id) {
    Address addressUpdate = addressMapper.toAddress(address);
    if (addressRepository.findById(id).isPresent()) {
      addressUpdate.setId(id);
      return addressMapper.toAddressResponse(addressRepository.save(addressUpdate));
    }
    return null;
  }

  @Transactional
  @CachePut(CACHE_FIND_BY_ID)
  public void delete(Long id) {
    if (addressRepository.findById(id).isPresent()) {
      addressRepository.deleteById(id);
    }
  }

}
