package hellojpa.v5;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class LockerV5 {
    @Id @GeneratedValue
    private Long id;
    private String name;
    @OneToOne(mappedBy = "locker")
    private MemberV5 member;
}
