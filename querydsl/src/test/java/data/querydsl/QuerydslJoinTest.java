package data.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import data.querydsl.entity.Member;
import data.querydsl.entity.QMember;
import data.querydsl.entity.QTeam;
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
import static data.querydsl.entity.QTeam.team;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class QuerydslJoinTest { // 8 조인
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
     * 팀 A 에 소속된 모든 회원
     * @throws Exception
     */
    @DisplayName("JOIN 테스트")
    @Test
    public void joinTest() throws Exception{
        //given
        List<Member> result = queryFactory
                .selectFrom(member)
                .leftJoin(member.team, team)
                .where(team.name.eq("teamA"))
                .fetch();
        //when

        //then
        assertThat(result).extracting("username").containsExactly("member1", "member2");
    }

    @DisplayName("연관관계가 없어도 JOIN 테스트 세타조인")
    @Test
    public void thetaJoinTest2() throws Exception{
        //given
        em.persist(new Member("teamA"));
        em.persist(new Member("teamB"));
        em.persist(new Member("teamC"));
        List<Member> result = queryFactory
                .select(member)
                .from(member, team)
                .where(member.username.eq(team.name))
                .fetch();
        //when

        //then
        result.forEach(System.out::println);
        assertThat(result).extracting("username").contains("teamA", "teamB");
    }
}
