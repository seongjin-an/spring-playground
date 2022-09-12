package data.querydsl.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UserDto {
    private String name;
    private int age;

    public UserDto(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
