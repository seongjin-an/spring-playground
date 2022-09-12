package data.querydsl.advanced;

import com.querydsl.jpa.impl.JPAQueryFactory;
import data.querydsl.entity.Member;
import data.querydsl.entity.QMember;
import data.querydsl.entity.Team;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static data.querydsl.entity.QMember.member;

@SpringBootTest
@Transactional
public class QuerydslBulk {
    @PersistenceContext
    EntityManager em;

    JPAQueryFactory queryFactory;

    @BeforeEach
    void before() {
        queryFactory = new JPAQueryFactory(em);

        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");

        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);
    }

    @DisplayName("벌크 연산")
    @Test
    public void bulkTest() throws Exception{
        //member1 = 10 -> 삐회원
        //member2 = 20 -> 삐회원
        //member3 = 30 -> 유지
        //member4 = 40 -> 유지

        long count = queryFactory
                .update(member)
                .set(member.username, "비회원")
                .where(member.age.lt(28))
                .execute();
        Assertions.assertThat(count).isEqualTo(2);
        //벌크 연산은 영속성 컨텍스트 리프레쉬 없이 SQL 문이 나간다. 즉 DB 상태와 영속성 상태가 다르게 됨.

        //1 member1 = 10 -> 1 DB 비회원
        //2 member2 = 20 -> 2 DB 비회원
        //3 member3 = 30 -> 3 DB member3
        //4 member4 = 40 -> 4 DB member4
        em.flush();
        em.clear();
        List<Member> result = queryFactory.selectFrom(member).fetch();
        for (Member member1 : result) {
            System.out.println("member1 = " + member1);
        }
    }

    @DisplayName("BULK ADD")
    @Test
    public void bulkAdd() throws Exception{
        long count = queryFactory.update(member).set(member.age, member.age.add(1)).execute();
        long count2 = queryFactory.update(member).set(member.age, member.age.multiply(1)).execute();
    }

    @DisplayName("BULK DELETE")
    @Test
    public void bulkDelete() throws Exception{
        long count = queryFactory.delete(member).where(member.age.gt(18)).execute();
    }
}
