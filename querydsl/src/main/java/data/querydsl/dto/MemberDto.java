package data.querydsl.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
public class MemberDto {
    private String username;
    private int age;

    @QueryProjection
    public MemberDto(String username, int age) {
        this.username = username;
        this.age = age;
    }
}
