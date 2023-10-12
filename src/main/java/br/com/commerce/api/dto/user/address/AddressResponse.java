package br.com.commerce.api.dto.user.address;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse implements Serializable {
    private Long id;
    private String place;
    private String zipcode;
    private String street;
    private String number;
    private String complement;
    private String reference;
    private String neighborhood;
    private String city;
    private String state;
    private String country;
}
