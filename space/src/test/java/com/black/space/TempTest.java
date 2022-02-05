package com.black.space;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

//@WebMvcTest
//@MockBean(JpaMetamodelMappingContext.class)
@SpringBootTest
public class TempTest {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeEach
    void bofore(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void helloWorld() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/hello"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("hello world"));
    }
}

/*
WebMvcTest 테스트 시
jpa 설정때문에 테스트 통과를 못하는 경우가 있는데
1.@MockBean(JpaMetamodelMappingContext.class)
2.***Application 모듈에서 JPA설정 어노테이션을 제거하고
  JPA 설정을 하는 모듈을 별도로 만든다. JpaConfig...
  @Configuration, @EnableJpaAuditing, ...등을 추가한다
  이렇게 하면 스프링 컨텍스트 로드시 jpa로드에서 제외됨
3.SpringBootTest를 사용한다...
  위처럼 한다...
 */