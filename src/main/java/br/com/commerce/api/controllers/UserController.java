package br.com.commerce.api.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.commerce.api.dto.user.UserRequest;
import br.com.commerce.api.dto.user.UserResponse;
import br.com.commerce.api.services.UserService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping(value = { "/users" })
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll() {
        log.info(this.getClass().getName() + " | " + "findAll");
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable UUID id) {
        log.info(this.getClass().getName() + " | " + "findById: " + id);
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody @Valid UserRequest user) {
        log.info(this.getClass().getName() + " | " + "create: " + user.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable UUID id,
            @RequestBody @Valid UserRequest user) {
        log.info(this.getClass().getName() + " | " + "update: " + id + user.toString());
        return ResponseEntity.status(HttpStatus.OK).body(userService.update(user, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        userService.delete(id);
        log.info(this.getClass().getName() + " | " + "delete: " + id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
