package br.com.commerce.api;

import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.commerce.api.models.Product;

@SpringBootTest
class ApiApplicationTests {

    @Test
    void testDoProduct() {
        Product product = new Product();
        assertNull(product.getId());
    }

}
