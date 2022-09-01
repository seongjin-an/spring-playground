package hellojpa.v3;

import hellojpa.MemberV2;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TeamV3 {
    @Id @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;
    private String name;

    @OneToMany
    @JoinColumn(name = "TEAM_ID")
    private List<MemberV3> members = new ArrayList<>();//일대다 연관관계 주인

//    public void addMember(Member member) {
//        member.setTeam(this);
//        members.add(member);
//    }


    public List<MemberV3> getMembers() {
        return members;
    }

    public void setMembers(List<MemberV3> members) {
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
