package jpql.join;

import jpql.Member;
import jpql.MemberType;
import jpql.Team;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Team teamA = new Team();
            teamA.setName("팀A");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("팀B");
            em.persist(teamB);

            Member member1 = new Member();
            member1.setUsername("회원1");
            member1.changeTeam(teamA);
            member1.setType(MemberType.ADMIN);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("회원2");
            member2.changeTeam(teamA);
            member2.setType(MemberType.ADMIN);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("회원2");
            member3.changeTeam(teamB);
            member3.setType(MemberType.ADMIN);
            em.persist(member3);

            em.flush();
            em.clear();

//            String query = "select m from Member m left join m.team t on m.username = t.name";
//            List<Member> result = em.createQuery(query, Member.class)
//                    .getResultList();
//            System.out.println("result.size = " + result.size());

            String query = "select m from Member m join fetch m.team";
            List<Member> result = em.createQuery(query, Member.class)
                    .getResultList();
            // 페치 조인을 했을 때와 안했을 때를 비교해보자
            for(Member member : result) {
                System.out.println("member = " + member.getUsername() + ", teamName = " + member.getTeam().getName());
                //회원1, 팀A(SQL)
                //회원2, 팀A(1차캐시)
                //회원3, 팀B(SQL)
            }

            String query2 = "select distinct t from Team t join fetch t.members";
            List<Team> resultList = em.createQuery(query2, Team.class).getResultList();
            for(Team team : resultList) {
                System.out.println("team = " + team.getName() + " | "+ team.getMembers().size());
            }
            System.out.println("///////////////////////////////////////////////////////////////");
            String query3 = "select t from Team t";
            List<Team> result2 = em.createQuery(query3, Team.class)
                    .setFirstResult(0)
                    .setMaxResults(2)
                    .getResultList();
            System.out.println("result = " + result.size());
            for(Team team : result2) {
                System.out.println("team = " + team.getName() + " | members = " + team.getMembers().size());
                for(Member member : team.getMembers()) {
                    System.out.println("-> member = " + member);
                }
            }

            tx.commit();
        } catch (Exception error) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
