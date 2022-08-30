package jpabook.jpashop.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ORDERS")
public class Order {
    @Id @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    @Column(name = "MEMBER_ID")
    private Long memberId;

    private LocalDateTime orderDate; // ORDER_DATE, order_date

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
