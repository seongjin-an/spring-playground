package hellojpa.v4;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class LockerV4 {
    @Id @GeneratedValue
    private Long id;
    private String name;
    @OneToOne(mappedBy = "locker")
    private MemberV4 member;
}
