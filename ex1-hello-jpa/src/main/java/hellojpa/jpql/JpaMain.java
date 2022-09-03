package hellojpa.jpql;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            //JPQL
//            List<Member> result = em.createQuery(
//                    "SELECT m FROM Member m WHERE m.username like '%kim%'",
//                    Member.class
//            ).getResultList();

            //CRITERIA
//            CriteriaBuilder cb = em.getCriteriaBuilder();
//            CriteriaQuery<Member> query = cb.createQuery(Member.class);
//            Root<Member> m = query.from(Member.class);
//            CriteriaQuery<Member> cq = query.select(m);
//
//            String username = "ansj";
//            if(username != null) {
//                cq = cq.where(cb.equal(m.get("username"), "kim"));
//            }
//
//            List<Member> resultList = em.createQuery(cq).getResultList();

            //Native query
//            em.createNativeQuery("select member_id, city, street, zipcode, username from member", Member.class).getResultList();


            tx.commit();
        } catch (Exception error) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
