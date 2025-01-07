package com.fullstack.shop.web.routine.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Promotions {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "promotion_type")
    private Double promotionType;

    @ManyToOne
    @JoinColumn(name = "prod_id", referencedColumnName = "id")
    private Product product;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "start_date")
    private LocalDateTime startDate;
}
