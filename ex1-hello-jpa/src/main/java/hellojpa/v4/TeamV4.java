package hellojpa.v4;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TeamV4 {
    @Id @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;
    private String name;

    @OneToMany
    @JoinColumn(name = "TEAM_ID")
    private List<MemberV4> members = new ArrayList<>();//일대다 연관관계 주인

//    public void addMember(Member member) {
//        member.setTeam(this);
//        members.add(member);
//    }


    public List<MemberV4> getMembers() {
        return members;
    }

    public void setMembers(List<MemberV4> members) {
        this.members = members;
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

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", members=" + members +
                '}';
    }
}
