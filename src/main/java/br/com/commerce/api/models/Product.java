package br.com.commerce.api.models;


import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Product")
@Table(name = "tb_product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column
    private String description;

    @Column
    private Double price;



}
