package hellojpa.v5;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class MemberProduct {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private MemberV5 member;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private ProductV5 product;

    private int count;
    private int price;

    private LocalDateTime orderDateTime;
}
