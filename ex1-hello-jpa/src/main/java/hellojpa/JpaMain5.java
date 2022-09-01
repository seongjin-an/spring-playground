package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain5 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Member member = new Member();
            member.setName("hello");

            em.persist(member);
            em.flush();
            em.clear();
            System.out.println("CHECK POINT1");
            Member findMember = em.getReference(Member.class, member.getId());//PROXY
            System.out.println("findMember.getClass() = " + findMember.getClass());
            System.out.println("findMember.id = " + findMember.getId());
            System.out.println("findMember.username = " + findMember.getName());

            System.out.println("CHECK POINT2");

            em.clear();
            Member m1 = em.find(Member.class, member.getId());//Member
            System.out.println("m1 = " + m1.getClass());

            Member reference = em.getReference(Member.class, member.getId());//Member
            System.out.println("reference = " + reference.getClass());
            //프록시가 아닌 실제 엔티티 반환// 영속성 컨텍스트에 이미 적재되었다면 굳이 프록시를 사용하지 않음

            System.out.println("a == a: " + (m1 == reference));
            //영속성 컨텍스트에 이미 적재되었던 것을 찾았기 때문

            em.clear();

            Member refMember = em.getReference(Member.class, member.getId());//Proxy
            System.out.println("refMember = " + refMember.getClass());

            System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(refMember));
            Member findMem = em.find(Member.class, member.getId());//Proxy
            //실제 엔티티가 아닌 프록시를 반환해버림, 이미 조회된 프록시가 있어서...
            System.out.println("findMem = " + findMem.getClass());

            System.out.println("refMember == findMem : " + (refMember == findMem));

            tx.commit();
        } catch (Exception error) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
