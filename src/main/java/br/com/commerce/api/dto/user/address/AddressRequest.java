package br.com.commerce.api.dto.user.address;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddressRequest {
    private String user;
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
