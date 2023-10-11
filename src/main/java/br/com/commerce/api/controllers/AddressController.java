package br.com.commerce.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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

import br.com.commerce.api.dto.user.address.AddressRequest;
import br.com.commerce.api.dto.user.address.AddressResponse;
import br.com.commerce.api.services.AddressService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping(value = { "/address" })
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping
    @Cacheable("AddressController.findAll")
    public ResponseEntity<List<AddressResponse>> findAll() {
        log.info(this.getClass().getName() + " | " + "findAll");
        return ResponseEntity.ok(addressService.findAllAddresss());
    }

    @GetMapping("/{id}")
    @Cacheable("AddressController.findById")
    public ResponseEntity<AddressResponse> findById(@PathVariable Long id) {
        log.info(this.getClass().getName() + " | " + "findById: " + id);
        return ResponseEntity.ok(addressService.findById(id));
    }

    @PostMapping
    public ResponseEntity<AddressResponse> create(@RequestBody @Valid AddressRequest user) {
        log.info(this.getClass().getName() + " | " + "create: " + user.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(addressService.save(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressResponse> update(@PathVariable Long id,
            @RequestBody @Valid AddressRequest user) {
        log.info(this.getClass().getName() + " | " + "update: " + id + user.toString());
        return ResponseEntity.status(HttpStatus.OK).body(addressService.update(user, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        addressService.delete(id);
        log.info(this.getClass().getName() + " | " + "delete: " + id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
