package data.querydsl;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPAExpressions;
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
public class QuerydslSubqueryTest { // 11. 서브쿼리 테스트
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

    /**
     * 나이가 가장 많은 회원 조회
     * @throws Exception
     */
    @DisplayName("서브쿼리 테스트-나이가 가장 많은 회원 조회")
    @Test
    public void subqueryTest() throws Exception{
        QMember memberSub = new QMember("memberSub");
        //given
        List<Member> result = queryFactory
                .selectFrom(member)
                .where(member.age.eq(
                        JPAExpressions.select(memberSub.age.max())
                                .from(memberSub)
                ))
                .fetch();
        //when

        //then
        Assertions.assertThat(result).extracting("age").containsExactly(40);
    }

    /**
     * 나이가 평균 이상인 회원
     * @throws Exception
     */
    @DisplayName("서브쿼리 테스트-나이가 평균 이상인 회원")
    @Test
    public void subqueryTest2() throws Exception{
        QMember memberSub = new QMember("memberSub");
        //given
        List<Member> result = queryFactory
                .selectFrom(member)
                .where(member.age.goe(
                        JPAExpressions.select(memberSub.age.avg())
                                .from(memberSub)
                ))
                .fetch();
        //when

        //then
        Assertions.assertThat(result).extracting("age").containsExactly(30, 40);
    }


    @DisplayName("SUBQUERY IN TEST")
    @Test
    public void subqueryInTest() throws Exception{
        QMember memberSub = new QMember("memberSub");
        //given
        List<Member> result = queryFactory
                .selectFrom(member)
                .where(member.age.in(
                        JPAExpressions.select(memberSub.age)
                                .from(memberSub)
                                .where(memberSub.age.gt(10))
                ))
                .fetch();
        //when

        //then
        Assertions.assertThat(result).extracting("age").containsExactly(20, 30, 40);
    }

    @DisplayName("SELECT SUBQUERY")
    @Test
    public void selectSubQuery() throws Exception{
        //given
        QMember memberSub = new QMember("memberSub");
        List<Tuple> tuple = queryFactory
                .select(member.username, JPAExpressions.select(memberSub.age.avg()).from(memberSub))
                .from(member)
                .fetch();

        //when

        //then
        for (Tuple tuple1 : tuple) {
            System.out.println("tuple = " + tuple);
        }
    }
}
