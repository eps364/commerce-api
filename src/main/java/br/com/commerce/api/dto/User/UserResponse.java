package br.com.commerce.api.dto.User;

import java.util.UUID;

import br.com.commerce.api.dto.User.Address.AddressResponse;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private UUID id;
    private String name;
    private String email;
    private String document;
    private List<AddressResponse> address;

}
