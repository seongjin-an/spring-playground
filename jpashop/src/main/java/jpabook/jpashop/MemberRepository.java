package jpabook.jpashop;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {
    @PersistenceContext//스프링 부트가 이 어노테이션이 있으면 엔티티 매니저를 주입해준다.
    private EntityManager em;

    public Long save(Member member) {
        em.persist(member);
        return member.getId();
        //member를 반환해도 되지만 아이디를 반환했는데
        //커맨드랑 쿼리를 분리해라 원칙
    }

    public Member find(Long id) {
        return em.find(Member.class, id);
    }
}
