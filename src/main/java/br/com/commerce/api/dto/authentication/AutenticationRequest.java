package br.com.commerce.api.dto.authentication;

public record AutenticationRequest (
    String username, 
    String password) {
}
