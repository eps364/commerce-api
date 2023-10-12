package br.com.commerce.api.dto.user;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import br.com.commerce.api.dto.user.address.AddressResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse implements Serializable{
    private UUID id;
    private String name;
    private String email;
    private String document;
    private List<AddressResponse> address;

}
