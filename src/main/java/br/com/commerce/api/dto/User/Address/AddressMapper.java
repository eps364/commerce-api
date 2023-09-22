package br.com.commerce.api.dto.User.Address;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.commerce.api.models.Address;


@Component
public class AddressMapper {

    @Autowired
    private ModelMapper mapper;

    public Address toAddress(AddressRequest address) {
        return mapper.map(address, Address.class);
    }

    public AddressRequest toAddressRequest(Address address) {
        return mapper.map(address, AddressRequest.class);
    }

    public AddressResponse toAddressResponse(Address optional) {
        return mapper.map(optional, AddressResponse.class);
    }

    public AddressResponse toAddressResponse(Optional<Address> optional) {
        return mapper.map(optional, AddressResponse.class);
    }

    public List<AddressResponse> toListAddressResponse(List<Address> listAddress) {
        return listAddress.stream()
                .map(this::toAddressResponse)
                .collect(Collectors.toList());
    }

}
