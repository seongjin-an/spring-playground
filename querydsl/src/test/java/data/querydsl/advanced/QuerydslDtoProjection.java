package data.querydsl.advanced;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import data.querydsl.dto.MemberDto;
import data.querydsl.dto.UserDto;
import data.querydsl.entity.Member;
import data.querydsl.entity.QMember;
import data.querydsl.entity.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static data.querydsl.entity.QMember.member;

@SpringBootTest
@Transactional
public class QuerydslDtoProjection { // 2 DTO 프로젝션
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

    @DisplayName("JPQL DTO 프로젝션")
    @Test
    public void jpqlDtoProjection() throws Exception{
        List<MemberDto> result = em.createQuery("select new data.querydsl.dto.MemberDto(m.username, m.age) " +
                        "from Member m", MemberDto.class)
                .getResultList();
        for (MemberDto memberDto : result) {
            System.out.println("memberDto = " + memberDto);
        }
    }

    @DisplayName("Querydsl DTO setter 프로젝션")
    @Test
    public void querydslSetterProjection() throws Exception{
        //setter 메서드를 이용할때는 이렇게 dto의 멤버변수들과 entity의 멤버 변수가 일치해야한다
        List<MemberDto> result = queryFactory
                .select(Projections.bean(MemberDto.class, member.username, member.age))
                .from(member)
                .fetch();
        for (MemberDto memberDto : result) {
            System.out.println("memberDto = " + memberDto);
        }
    }

    @DisplayName("Querydsl DTO fields 프로젝션")
    @Test
    public void querydslFieldProjection() throws Exception{
        //setter와 동일하게 멤버변수들의 명칭이 일치해야한다.
        List<MemberDto> result = queryFactory
                .select(Projections.fields(MemberDto.class, member.username, member.age))
                .from(member)
                .fetch();
        for (MemberDto memberDto : result) {
            System.out.println("memberDto = " + memberDto);
        }
    }

    @DisplayName("Querydsl setter, fields 프로젝션 - alias 를 사용해서라도 명칭을 일치시켜야 한다.")
    @Test
    public void userDtoProjection() throws Exception{
        //setter 나 fields 는 명칭이 일치하지 않으면 제대로 프로젝션이 되지 않는다. 그래서 as 를 사용해서 반드시 명칭을 일치시켜야 함.!!!
        List<UserDto> result = queryFactory
                .select(Projections.fields(UserDto.class, member.username.as("name"), member.age))
                .from(member)
                .fetch();
        for (UserDto userDto : result) {
            System.out.println("memberDto = " + userDto);
        }
    }

    @DisplayName("Querydsl 서브쿼리 프로젝션 alias 프로젝션 예제")
    @Test
    public void subqueryProjection() throws Exception{
        QMember memberSub = new QMember("memberSub");
        List<UserDto> result = queryFactory
                .select(
                        Projections.fields(
                            UserDto.class,
//                            member.username.as("name"),
                            ExpressionUtils.as(member.username, "name"),//위에 것과 같은데 통잃감을 위해 ExpressionUtils 사용
                            ExpressionUtils.as(JPAExpressions.select(memberSub.age.max()).from(memberSub), "age")
                        )
                )
                .from(member)
                .fetch();
        for (UserDto userDto : result) {
            System.out.println("memberDto = " + userDto);
        }
    }

    @DisplayName("Querydsl DTO 생성자 프로젝션")
    @Test
    public void querydslConstructorProjection() throws Exception{
        //생성자의 경우 타입만 맞으면 된다.
        List<MemberDto> result = queryFactory
                .select(Projections.constructor(MemberDto.class, member.username, member.age))
                .from(member)
                .fetch();
        for (MemberDto memberDto : result) {
            System.out.println("memberDto = " + memberDto);
        }
    }

    @DisplayName("Querydsl 생성자 프로젝션은 타입만 일치하면 된다.")
    @Test
    public void constructorProjection() throws Exception{
        //생성자의 경우 타입만 맞으면 된다.
        List<UserDto> result = queryFactory
                .select(Projections.constructor(UserDto.class, member.username, member.age))
                .from(member)
                .fetch();
        for (UserDto userDto : result) {
            System.out.println("userDto = " + userDto);
        }
    }


}
