package com.fullstack.shop.web.routine.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer amount;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "cus_id", referencedColumnName = "id")
    private Customer customer;
}
