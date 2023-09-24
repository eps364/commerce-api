package br.com.commerce.api.models;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Address")
@Table(name = "tb_address")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private String place;

    @Column
    private String zipcode;

    @Column
    private String street;

    @Column
    private String number;

    @Column
    private String complement;

    @Column
    private String reference;

    @Column
    private String neighborhood;

    @Column
    private String city;

    @Column
    private String state;

    @Column
    private String country;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_update")
    private Instant dateUpdate;

    @PreUpdate
    public void preUpdate() {
        this.dateUpdate = Instant.now();
    }
}
