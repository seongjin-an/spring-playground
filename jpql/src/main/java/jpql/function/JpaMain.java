package jpql.function;

import jpql.Member;
import jpql.MemberType;
import jpql.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Member member1 = new Member();
            member1.setUsername("관리자1");
            em.persist(member1);
            Member member2 = new Member();
            member2.setUsername("관리자2");
            em.persist(member2);

            em.flush();
            em.clear();

//            String query = "select 'a' || 'b' from Member m";
//            String query = "select concat('a', 'b') from Member m";
//            String query = "select substring(m.username, 2, 3) from Member m";
//            List<String> resultList = em.createQuery(query, String.class).getResultList();
            //trim(), lower(), upper(), length(), locate(), abs(), sqrt(), mod(), size(), index()
            String query = "select locate('de', 'abcdef') from Member m";
            List<Integer> resultList = em.createQuery(query, Integer.class).getResultList();
            for(Integer s : resultList) {
                System.out.println("s = " + s);
            }

            String query2 = "select size(t.members) from Team t";
            List<Integer> resultList2 = em.createQuery(query2, Integer.class).getResultList();
            for(Integer s : resultList2){
                System.out.println("s = " + s);
            }

            String query3 = "select function('group_concat', m.username) from Member m";
            List<String> resultList3 = em.createQuery(query3, String.class).getResultList();
            for(String s : resultList3){
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
