package br.com.commerce.api.models;

import java.time.Instant;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Order")
@Table(name = "tb_order")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Order {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderState state;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order") 
    private List<OrderItem> orderItems;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_create")
    private Instant dateCreate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_update")
    private Instant dateUpdate;

    @PreUpdate
    public void preUpdate() {
        this.dateUpdate = Instant.now();
    }
}
