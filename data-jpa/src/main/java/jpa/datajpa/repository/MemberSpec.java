package jpa.datajpa.repository;

import jpa.datajpa.entity.Member;
import jpa.datajpa.entity.Team;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;

public class MemberSpec {
    public static Specification<Member> teamName(final String teamName) {
        return new Specification<Member>() {
            @Override
            public Predicate toPredicate(Root<Member> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                if(StringUtils.isEmpty(teamName)) {
                    return null;
                }
                Join<Object, Object> team = root.join("team", JoinType.INNER);//회원과 조인
                Predicate name = builder.equal(team.get("name"), teamName);
                return name;
            }
        };
    }

    public static Specification<Member> username(final String username) {
        return (root, query, builder) -> builder.equal(root.get("username"), username);
    }
}
