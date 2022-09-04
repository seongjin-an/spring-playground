package jpql.expression;

import jpql.Member;
import jpql.MemberType;
import jpql.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMAin {
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
            member1.setUsername("관리자");
            member1.setAge(10);
            member1.changeTeam(teamA);
            member1.setType(MemberType.ADMIN);

            em.persist(member1);

            em.flush();
            em.clear();
            //타입 표현
//            String query = "select m.username, 'HELLO', true from Member m " +
//                    "where m.type = :userType";
//            List<Object[]> result = em.createQuery(query)
//                    .setParameter("userType", MemberType.ADMIN)
//                    .getResultList();
//            for(Object[] object : result) {
//                System.out.println("object[0] = " + object[0]);
//                System.out.println("object[1] = " + object[1]);
//                System.out.println("object[2] = " + object[2]);
//            }
            //조건 CASE 식
//            String query = "select " +
//                    "case when m.age <= 10 then '학생요금' " +
//                    "     when m.age >= 60 then '경로요금' " +
//                    "     else '일반요금' " +
//                    "end " +
//                    "from Member m";
//            List<String> resultList = em.createQuery(query, String.class).getResultList();
//            for (String s : resultList) {
//                System.out.println("s = " + s);
//            }

            //coalesce(컬럼1, 컬럼2, .., 컬럼N) : coalesce 함수는 처음으로 null 이 아닌 컬럼 값을 만낟면 그 컬럼 값을 리턴함.
//            String query = "select coalesce(m.username, '이름 없는 회원') from Member m";
//            List<String> resultList = em.createQuery(query, String.class).getResultList();
//            for(String s : resultList) {
//                System.out.println("s = " + s);
//            }

            //nullif(표현식1, 표현식2) : 표현식1 이 표현식2 와 같으면 NULL 반환
            String query = "select nullif(m.username, '관리자') as username from Member m";
            List<String> resultList = em.createQuery(query, String.class).getResultList();
            for(String s : resultList) {
                System.out.println("s = " + s);
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
