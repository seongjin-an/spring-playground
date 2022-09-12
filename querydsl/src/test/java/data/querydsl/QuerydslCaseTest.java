package data.querydsl;

import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import data.querydsl.entity.Member;
import data.querydsl.entity.QMember;
import data.querydsl.entity.Team;
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
public class QuerydslCaseTest { // 12 CASE
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

    @DisplayName("CASE 문 단순한 건 테스트")
    @Test
    public void caseBaseTest() throws Exception{
        //given
        List<String> result = queryFactory.select(member.age.when(10).then("열살").when(20).then("스무살").otherwise("기타"))
                .from(member)
                .fetch();
        //when

        //then
        for (String s : result) {
            System.out.println("s = " + s);
        }
    }

    @DisplayName("CASE 문 복잡한 건 테스트")
    @Test
    public void caseComplexTest() throws Exception{
        //given
        List<String> result = queryFactory
                .select(new CaseBuilder()
                        .when(member.age.between(0, 20)).then("0~20살")
                        .when(member.age.between(21, 30)).then("21~30살")
                        .otherwise("기타")
                )
                .from(member)
                .fetch();
        //when

        //then
        for (String s : result) {
            System.out.println("s = " + s);
        }
    }
}
