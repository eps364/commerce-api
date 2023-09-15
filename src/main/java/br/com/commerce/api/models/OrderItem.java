package br.com.commerce.api.models;


import jakarta.persistence.*;

@Entity(name = "OrderItem")
@Table(name = "tb_order_item")
public class OrderItem {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "order_id")
    private Long order;

    @Column(name = "product_id")
    private Long product;

    @Column(name = "price")
    private double price;

    @Column(name = "quantity")
    private int quantity;
}
