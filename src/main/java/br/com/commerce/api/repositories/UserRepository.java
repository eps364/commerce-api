package br.com.commerce.api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import br.com.commerce.api.models.User;

@Repository
public interface UserRepository extends JpaRepository<User,UUID>{

    UserDetails findByEmail(String email);
    
}
