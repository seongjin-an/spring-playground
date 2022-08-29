package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.function.Consumer;

public class JpaMain3 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Team team = new Team();
            team.setName("TeamA");
//            team.getMembers().add(member);
            em.persist(team);


            Member member = new Member();
            member.setName("member1");
            member.setTeam(team); //**
//            team.addMember(member);
            em.persist(member);
//            team.getMembers().add(member); //** // 연관관계 편의 메서드를 생성하자!!
//            em.flush();
//            em.clear();

            Team findTeam = em.find(Team.class, team.getId());
            List<Member> members = findTeam.getMembers();
            members.forEach(member1 -> System.out.println("member: " + member1.getName()));
            System.out.println("members = " + findTeam);

            tx.commit();
        } catch (Exception error) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
