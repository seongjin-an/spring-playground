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
            teamA.setName("teamA");
            em.persist(teamA);

            Member member1 = new Member();
            member1.setUsername("teamA");
            member1.setAge(10);
            member1.changeTeam(teamA);
            member1.setType(MemberType.ADMIN);

            em.persist(member1);

            em.flush();
            em.clear();

            String query = "select m from Member m left join m.team t on m.username = t.name";
            List<Member> result = em.createQuery(query, Member.class)
                    .getResultList();
            System.out.println("result.size = " + result.size());
            tx.commit();
        } catch (Exception error) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
