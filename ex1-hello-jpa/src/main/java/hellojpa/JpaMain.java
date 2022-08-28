package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        //persistence.xml persistence-unit name
        EntityManager em = emf.createEntityManager();//쉽게 생각해서, DB 커넥션 하나 받았다고 생각하면 될듯

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            //CREATE
//            Member member = new Member();
//            member.setId(1L);
//            member.setName("Hello");
//            em.persist(member);

            //UPDATE
            Member findMember = em.find(Member.class, 1L);
            findMember.setName("HelloJPA");

            //JPQL
            List<Member> findMembers = em.createQuery("SELECT m FROM Member m", Member.class).getResultList();

            tx.commit();
        } catch (Exception error) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
