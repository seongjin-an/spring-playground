package com.black.space.repository;

import com.black.space.domain.Gender;
import com.black.space.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
public class EntityManagerTest {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;
    @Test
    void insertTest(){
        User user = new User();
        user.setName("ansj3");
        user.setGender(Gender.MALE);
        user.setEmail("ansj3@abc.com");
        userRepository.save(user);
        userRepository.flush();
    }
    @Test
    public void entityManagerTest() {
        //JPQL
        System.out.println(entityManager.createQuery("SELECT u FROM User u").getResultList());
    }

    @Test
    void cacheFindTest(){
        System.out.println(userRepository.findByEmail("ansj@abc.com"));
        System.out.println(userRepository.findByEmail("ansj@abc.com"));
        System.out.println(userRepository.findByEmail("ansj@abc.com"));
//        System.out.println(userRepository.findById(2L).get());
//        System.out.println(userRepository.findById(2L).get());
//        System.out.println(userRepository.findById(2L).get());
        /*
        save시점에 db에 바로 반영 안함
        사용하는 영속성 context와 실제 db 사이에 일종의 데이터 gap이 발생함..
        영속성 context에서 jpa에 대해 캐싱처리함.. jpa1차 캐시라고 함
        PK ID로 조회하는 경우만임
        조회성능이 올라간다.
         */
    }

    @Test
    public void deleteTest(){
        userRepository.deleteById(2L);
        //test 에서 Transactional 어노테이션을 달고 delete 시 db 에 반영되지 않음,
        //rollback
    }


    @Test
    void cacheFindTest2(){
        User user = userRepository.findById(2L).get();
        user.setName("ansj");
        userRepository.save(user);
        System.out.println("=====================================");
        user.setEmail("ansj@abc.com");
        userRepository.save(user);
        System.out.println("=====================================");
        System.out.println(">>>1: " + userRepository.findById(2L).get());
        userRepository.flush();
        System.out.println(">>>2: " + userRepository.findById(2L).get());

        /*
        transactional어노테이션이 없을 경우 save 마다 마다 바로 반영하는데
        전체를 하나의 transaction 어노테이션으로 묶을 경우
        영속성 context에서 데이터를 관리하다가 최종적으로 반영하게 됨.

        위 1번의 결과는 업데이트가 DB에 반영되고 DB에서 재조회해온 것처럼 보이는데
        Transactional 이 걸려있으므로 아직 영속성이 DB에 반영되기 이전이라는 점이다.
        즉, 영속성 캐시데이터를 보여준거고
        이후 플러쉬를 수행하고 나서야 2번에서 DB와 영속성 컨텍스트가 일치되어 진 점을 확인했다.

        하지만 flush() 하지 않은 채 findAll() 명령을 실행하게 되면 어떻게 될까
        이때는 영속성 context를 알아서 flush 하고 이후에 findAll 하여 영속성을 최신화 한다.
         */
    }
}
