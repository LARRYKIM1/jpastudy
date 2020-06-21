package com.larrykim.jpastudy;

import com.larrykim.jpastudy.entity.Item.Book;
import com.larrykim.jpastudy.entity.Item.Item;
import com.larrykim.jpastudy.entity.OrderItem;
import com.larrykim.jpastudy.func.PrintVisitor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ParentChildProxyTest {
    @PersistenceUnit
    EntityManagerFactory emf;

    @PersistenceContext
    EntityManager em;

    @Test
    public void 비지터_패턴_사용_3 (){
        OrderItem orderItem = generateOrderItem();

        Item item = orderItem.getItem();

        System.out.println("1. 첫번째");
        item.accept(new PrintVisitor());

        //프록시를 부모 타입으로 조회하면,
        //부모의 타입을 기반으로 프록시가 생성되는 문제를 해결하기 위해
        //서브클래스 타입을 확인하기 위한 방법에 대해 테스트해본 것이다.

    }

    public OrderItem generateOrderItem(){

        //https://reinhard.codes/2015/09/16/lomboks-builder-annotation-and-inheritance/
        //상속관계 builder시 문제 해결법
        Book savedBook = Book.builder().author("kim").name("bookname").build();

        System.out.println("========================================");
        System.out.println("빌더 생성 확인 = "+savedBook);
        System.out.println("========================================");

        OrderItem orderItem = new OrderItem();
        orderItem.setItem(savedBook);

        // 주문아이템과 책이 같이 저장, CascasdeType
        em.persist(orderItem);
        em.flush();
        em.clear();

        return em.find(OrderItem.class, orderItem.getId());
    }

}
