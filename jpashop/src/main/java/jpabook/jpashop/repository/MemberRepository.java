package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;
    //만약 entityManagerFactory를 주입받고 싶다면, @PersistenceUnit 을 사용하면 된다.
    public void save(Member member) {
        em.persist(member);//영속성 컨텍스트에 객체삽입
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() { //ctrl + alt + n : inline화 한다.
        return em.createQuery("SELECT m FROM Member m", Member.class).getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("SELECT m FROM Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
