package br.com.commerce.api.dto.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.commerce.api.models.User;

@Component
public class UserMapper {

    @Autowired
    private ModelMapper mapper;

    public User toUser(UserRequest user) {
        return mapper.map(user, User.class);
    }

    public User toUser(UserResponse user) {
        return mapper.map(user, User.class);
    }

    public UserRequest toUserRequest(User user) {
        return mapper.map(user, UserRequest.class);
    }

    public UserResponse toUserResponse(User optional) {
        return mapper.map(optional, UserResponse.class);
    }

    public UserResponse toUserResponse(Optional<User> optional) {
        return mapper.map(optional, UserResponse.class);
    }

    public List<UserResponse> toListUserResponse(List<User> listUser) {
        return listUser.stream()
                .map(this::toUserResponse)
                .collect(Collectors.toList());
    }

}
