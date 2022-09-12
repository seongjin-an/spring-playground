package data.querydsl;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import data.querydsl.entity.Member;
import data.querydsl.entity.QMember;
import data.querydsl.entity.QTeam;
import data.querydsl.entity.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static data.querydsl.entity.QMember.member;
import static data.querydsl.entity.QTeam.team;

@SpringBootTest
@Transactional
@Rollback(false)
public class QuerydslJoinOnTest { // 9 조인 ON 절 테스트
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
     * 예) 회원과 팀을 조인하면서, 팀 이름이 teamA 인 팀만 조인, 회원은 모두 조회
     * JPQL: select m, t from Member m left join m.team t on t.name = 'teamA'
     * @throws Exception
     */
    @DisplayName("조인 대상 필터링!!(외부조인)")
    @Test
    public void onTest() throws Exception{
        //given
        //1
//        List<Member> result = queryFactory
//                .select(member)
//                .from(member, team)
////                .leftJoin(member.team, team)
////                .on(team.name.eq("teamA"))
//                .fetch();
        //2
//        List<Tuple> result = queryFactory
//                .select(member, team)
//                .from(member)
//                .join(team)
//                .on(member.team.id.eq(team.id))
////                .leftJoin(member.team, team)
////                .on(team.name.eq("teamA"))
//                .fetch();
        //3
//        List<Tuple> result = queryFactory
//                .select(member, team)
//                .from(member)
////                .leftJoin(member.team, team)
////                .on(team.name.eq("teamA"))
//                .fetch();
        //2, 3 은 DB 쿼리가 똑같음
//        List<Member> result = queryFactory
//                .select(member)
//                .from(member)
//                .join(member.team, team)
//                .on(team.name.eq("teamA"))
//                .fetch();
        List<Tuple> result = queryFactory
                .select(member, team)
                .from(member)
                .join(member.team, team)
//                .on(team.name.eq("teamA"))
                .where(team.name.eq("teamA"))//위 ON 절이랑 똑같음, 다만 외부 조인이면 반드시 ON 절을 사용해야 함!!
                .fetch();
        //Entity 상에서 연관관계가 설정되어 있으면 ON(식별키 = 식별키) 쿼리가 알아서 추가되넹
        //JOIN 은 Entity 상에서 연관관계를 설정하지 않은 것들에 조인을 걸려고 할때?
        //join(member.team, team) 이렇게 하면 join(team).on(member.team.eq(team)) 을 단축할 수도 있네?
        //즉 엔티티상의 연관관계가 맺어져 있다면, join(member.team, team) 하면 알아서 아이디 값으로 알아서 매칭이 되고
        //엔티티사으이 연관관계가 없었는데 조인을 하려면 .join(맺고 싶은 엔티티).on(조건) 이런식으로 맺어줘야 함.
        //when

        //then
        for (Tuple tuple : result) {
            System.out.println("tuple = " + tuple);
        }
    }

    /**
     * 연관관계가 없는 엔티티 외부 조인
     * 회원의 이름이 팀 이름과 같은 대상 외부 조인
     * @throws Exception
     */
    @DisplayName("연관관계가 없는 엔티티 외부조인")
    @Test
    public void joinOnNoRelation() throws Exception{
        //given
        em.persist(new Member("teamA"));
        em.persist(new Member("teamB"));
        em.persist(new Member("teamC"));
        List<Tuple> result = queryFactory
                .select(member, team)
                .from(member)
                .leftJoin(team)
                .on(member.username.eq(team.name))//ON 절은 필터링한다. 조인할 대상을 줄인다고 생각하자.
                .fetch();
        //when

        //then
        for (Tuple tuple : result) {
            System.out.println("tuple = " + tuple);
        }
    }
}
