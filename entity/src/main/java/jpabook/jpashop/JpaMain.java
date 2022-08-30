package jpabook.jpashop;

import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.domain.OrderStatus;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Order order = new Order();
            order.setStatus(OrderStatus.ORDER);
            em.persist(order);

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            em.persist(orderItem);

            em.flush();
            em.clear();

            OrderItem findOrderItem = em.find(OrderItem.class, orderItem.getId());
            System.out.println("findOrderItem: " + findOrderItem);//lazy

            tx.commit();
        } catch (Exception error) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
