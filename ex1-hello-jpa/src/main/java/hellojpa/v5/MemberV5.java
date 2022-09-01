package hellojpa.v5;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class MemberV5 {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(name = "USERNAME")
    private String name;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID", insertable = false, updatable = false)//읽기전용으로 되어버림
    private TeamV5 team;

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private LockerV5 locker;

    @OneToMany(mappedBy = "member")
    private List<MemberProduct> memberProducts = new ArrayList<>();

    public TeamV5 getTeam() {
        return team;
    }

    public void setTeam(TeamV5 team) {
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
