package jpabook.jpashop2.repository.order.simplequery;

import jpabook.jpashop2.dto.OrderSimpleQueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderSimpleQueryRepository {
    private final EntityManager em;
    public List<OrderSimpleQueryDto> findOrderDtos() {
        //entity 나 embeddable을 반환할 수 있기에 DTO 는 new 로 생성해야 함
        return em.createQuery(
                "select new jpabook.jpashop2.dto.OrderSimpleQueryDto(o.id, m.name, o.orderDate, o.status, d.address) " +
                        "from Order o " +
                        "join o.member m " +
                        "join o.delivery d",
                OrderSimpleQueryDto.class
        ).getResultList();
    }
}
