package hellojpa.v3;

import javax.persistence.*;

@Entity
public class Locker {
    @Id @GeneratedValue
    private Long id;
    private String name;
    @OneToOne(mappedBy = "locker")
    private MemberV3 member;
}
