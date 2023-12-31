package br.com.commerce.api.dto.user.address;

import java.util.List;
import java.util.Optional;
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

    public AddressResponse toAddressResponse(Address address) {
        return mapper.map(address, AddressResponse.class);
    }

    public AddressResponse toAddressResponse(Optional<Address> address) {
        return mapper.map(address, AddressResponse.class);
    }

    public List<AddressResponse> toListAddressResponse(List<Address> listAddress) {
        return listAddress.stream()
                .map(this::toAddressResponse)
                .toList();
    }

}
