package hellojpa.v3;

import hellojpa.TeamV2;

import javax.persistence.*;

@Entity
public class MemberV3 {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(name = "USERNAME")
    private String name;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID", insertable = false, updatable = false)//읽기전용으로 되어버림
    private TeamV3 team;

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

    public TeamV3 getTeam() {
        return team;
    }

    public void setTeam(TeamV3 team) {
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
