package jpql;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
//            Member member =  new Member();
//            member.setUsername("member1");
//            member.setAge(10);
//            em.persist(member);
            for(int i = 0; i < 100; i++){
                Member member = new Member();
                member.setUsername("member" + i);
                member.setAge(i);
                em.persist(member);
            }

            //반환 타입이 명확하지가 않다.
//            Query query2 = em.createQuery("select m.username, m.age from Member m");
            //반환 타입이 명확하다.
            TypedQuery<Member> query1 = em.createQuery("select m from Member m", Member.class);
            //결과가 리스트이다.
            List<Member> resultList = query1.getResultList();//결과가 없으면 빈 리스트를 반환한다.
            try {
                TypedQuery<Member> query3 = em.createQuery("select m from Member m where m.id = 100", Member.class);
            }catch(NoResultException | NonUniqueResultException e){
                //리스트는 결과가 없으면 빈 리스트를 반환해주는 주지만 single result 는
                //결과가 없으면 NoResultException, 둘 이상이면 NonUniqueResultException
                e.printStackTrace();
            }
            //동적 조회
            TypedQuery<Member> query = em.createQuery("select m from Member m where m.username = :username", Member.class);
            query.setParameter("username", "member1");
            Member singleResult = query.getSingleResult();
            System.out.println("singleResult = " + singleResult.getUsername());

            //엔티티 프로젝션
//            List<Member> result = em.createQuery("select m from Member m", Member.class).getResultList();
            //엔티티 프로젝션2
//            List<Team> result2 = em.createQuery("select t from Member m join m.team t", Team.class).getResultList();
            //임베디드 타입 프로젝션
//            List<Address> result3 = em.createQuery("select o.address from Order o", Address.class).getResultList();
            //스칼라 타입 프로젝션
//            List result4 = em.createQuery("select m.username, m.age from Member m").getResultList();
//            Object o = result4.get(0);
//            Object[] res = (Object[]) o;
//            System.out.println("res[0] = " + res[0]);
//            System.out.println("res[1] = " + res[1]);
            //스칼라 타입 프로젝션2
//            List<Object[]> result5 = em.createQuery("select m.username, m.age from Member m").getResultList();
//            Object[] res2 = result5.get(0);
//            System.out.println("res2[0] = " + res2[0]);
//            System.out.println("res2[1] = " + res2[1]);
            //DTO 프로젝션
//            List<MemberDTO> result6 = em.createQuery("select new jpql.MemberDTO(m.username, m.age) from Member m", MemberDTO.class).getResultList();
//            for (MemberDTO dto : result6) {
//                System.out.println("username = " + dto.getUsername());
//                System.out.println("age = " + dto.getAge());
//            }
            //페이징
            List<Member> pagingResult = em.createQuery("select m from Member m order by m.age desc", Member.class)
                    .setFirstResult(0)
                    .setMaxResults(10)
                    .getResultList();
            System.out.println("pagingResult.size = " + pagingResult.size());
            for (Member member1 : pagingResult) {
                System.out.println("member1 = " + member1);
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
