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
//            Member findMember = em.find(Member.class, 1L);
//            findMember.setName("HelloJPA");

            //JPQL
//            List<Member> findMembers = em.createQuery("SELECT m FROM Member m", Member.class).getResultList();

            //비영속
            Member member = new Member();
            member.setId(100L);
            member.setName("HelloJPA");

            System.out.println("=== BEFORE PERSIST ===");
            em.persist(member);//영속
            System.out.println("=== AFTER PERSIST ===");

            Member findMember = em.find(Member.class, 100L);
            System.out.println("findMember.id = " + findMember.getId());
            System.out.println("findMember.name = " + findMember.getName());
            Member findMember2 = em.find(Member.class, 100L);
            System.out.println("result(==) : " + (findMember == findMember2));

            System.out.println("====================LAZY INSERT=================");
            Member member1 = new Member(101L, "A");
            Member member2 = new Member(102L, "B");
            em.persist(member1);
            em.persist(member2);
            System.out.println("================LAZY INSERT================");

            System.out.println("=================dirty checking==================");
            Member member3 = em.find(Member.class, 102L);
            member3.setName("BBBBBBBBBBBBB");
//            em.persist(member3);//할 필요가 없음//알아서 더티체킹해서 변경해줌
            System.out.println("========================================");

//            em.clear();
            System.out.println("==============FLUSH=============");
            Member member4 = new Member(200L, "member200");
            em.persist(member4);
            em.flush();//flush() 를 하여도 1차 캐시는 계속 유지됨!!
            System.out.println("==============FLUSH=============");

            System.out.println("==============AUTO FLUSH AFTER JPQL=============");
            Member member5 = new Member(115L, "member115");
            Member member6 = new Member(116L, "member116");
            Member member7 = new Member(117L, "member117");
            em.persist(member5);
            em.persist(member6);
            em.persist(member7);
            List<Member> query = em.createQuery("SELECT m FROM Member m", Member.class).getResultList();
            query.forEach(_member -> {
                System.out.println("member id: " + _member.getId());
            });
            System.out.println("==============AUTO FLUSH AFTER JPQL=============");

            System.out.println("=============DETACHED ENTITY===========");
            member3.setName("detached entity");
            em.detach(member3);

            System.out.println("=============DETACHED ENTITY===========");

            System.out.println("=== BEFORE COMMIT===");
            tx.commit();
            System.out.println("=== AFTER COMMIT ===");
        } catch (Exception error) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
