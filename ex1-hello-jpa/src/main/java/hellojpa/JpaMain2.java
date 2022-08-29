package hellojpa;

import javax.persistence.*;

public class JpaMain2 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            //1. DB 테이블에 초점을 맞추어 데이터 중심으로 모델링하면 협력관계를 만들 수가 없다.
//            Team team = new Team();
//            team.setName("TeamA");
//            em.persist(team);
//
//            Member member = new Member();
//            member.setName("member1");
//            member.setTeamId(team.getId());
//            em.persist(member);
//
//            Member findMember = em.find(Member.class, member.getId());
//
//            Long findTeamId = findMember.getTeamId();
//            Team findTeam = em.find(Team.class, findTeamId);

            //2. 객체지향 모델링(단방향 연관관계)
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Team teamB = new Team();
            team.setName("TeamB");
            em.persist(teamB);

            Member member = new Member();
            member.setName("member1");
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, member.getId());
            Team findTeam = findMember.getTeam();
            System.out.println("findTeam = " + findTeam.getName());

//            Team newTeam = em.find(Team.class, teamB.getId());
            findMember.setTeam(teamB);

            tx.commit();
        } catch (Exception error) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
