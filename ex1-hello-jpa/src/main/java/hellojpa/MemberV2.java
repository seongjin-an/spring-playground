package hellojpa;

import javax.persistence.*;

@Entity
public class MemberV2 {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(name = "USERNAME")
    private String name;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID", insertable = false, updatable = false)//읽기전용으로 되어버림
    private TeamV2 team;

    public TeamV2 getTeam() {
        return team;
    }

    public void setTeam(TeamV2 team) {
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
