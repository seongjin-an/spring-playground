package hellojpa.v4;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class MemberV4 {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(name = "USERNAME")
    private String name;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID", insertable = false, updatable = false)//읽기전용으로 되어버림
    private TeamV4 team;

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private LockerV4 locker;

    @ManyToMany
    @JoinTable(name = "MEMBER_PRODUCT")
    private List<Product> products = new ArrayList<>();

    public TeamV4 getTeam() {
        return team;
    }

    public void setTeam(TeamV4 team) {
        this.team = team;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
