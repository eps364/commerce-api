package br.com.commerce.api.dto.User;

import java.util.UUID;

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
    private String password;
    private String document;

}
