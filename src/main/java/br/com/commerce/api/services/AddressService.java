package br.com.commerce.api.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.commerce.api.dto.User.UserMapper;
import br.com.commerce.api.dto.User.UserResponse;
import br.com.commerce.api.dto.User.Address.AddressMapper;
import br.com.commerce.api.dto.User.Address.AddressRequest;
import br.com.commerce.api.dto.User.Address.AddressResponse;
import br.com.commerce.api.models.Address;
import br.com.commerce.api.models.User;
import br.com.commerce.api.repositories.AddressRepository;

@Service
public class AddressService {

  @Autowired
  AddressRepository addressRepository;

  @Autowired
  AddressMapper addressMapper;

  @Autowired
  UserService userService;

  @Autowired
  UserMapper userMapper;

  public List<AddressResponse> findAllAddresss() {
    return addressMapper.toListAddressResponse(addressRepository.findAll());
  }

  public AddressResponse findById(Long id) {
    return addressMapper.toAddressResponse(addressRepository.findById(id));
  }

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

  public AddressResponse update(AddressRequest address, Long id) {
    Address addressUpdate = addressMapper.toAddress(address);
    if (addressRepository.findById(id).isPresent()) {
      addressUpdate.setId(id);
      return addressMapper.toAddressResponse(addressRepository.save(addressUpdate));
    }
    return null;
  }

  public void delete(Long id) {
    if (addressRepository.findById(id).isPresent()) {
      addressRepository.deleteById(id);
    }
  }

}
