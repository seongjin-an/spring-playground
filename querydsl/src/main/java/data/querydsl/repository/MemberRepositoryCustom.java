package data.querydsl.repository;

import data.querydsl.dto.MemberSearchCondition;
import data.querydsl.dto.MemberTeamDto;

import java.util.List;

public interface MemberRepositoryCustom {
    List<MemberTeamDto> search(MemberSearchCondition condition);
}
