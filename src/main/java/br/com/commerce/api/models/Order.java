package br.com.commerce.api.models;

import jakarta.persistence.*;

import java.util.Date;


@Entity(name = "Order")
@Table(name = "tb_order")
public class Order {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "state")
    private String state;

    @Column(name = "data_create")
    private Date dateCreate;

    @Column(name = "data_update")
    private Date dateUpdate;
}
