package com.larrykim.jpastudy;

import com.larrykim.jpastudy.entity.Item.Book;
import com.larrykim.jpastudy.entity.Item.Item;
import com.larrykim.jpastudy.entity.OrderItem;
import org.hibernate.Hibernate;
import org.junit.Assert;
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
    public void 부모타입으로_프록시조회 () {
        Book savedBook = new Book();
        savedBook.setName("jpabook");
        savedBook.setAuthor("kim");
        em.persist(savedBook);
        em.flush();
        em.clear();

        // 프록시를 부모 타입으로 조회
        Item proxyItem = em.getReference(Item.class, savedBook.getId());

        if (proxyItem instanceof Book) { // false
            System.out.println("proxyitem instanceof Book");
            Book book = (Book) proxyItem;
            System.out.println("책 저자 = " + book.getAuthor());
        } else {
            System.out.println("부모타입프록시를 자식타입하고 instanceof 비교할 수 없습니다.");
        }

        Assert.assertFalse( proxyItem.getClass() == Book.class );
        Assert.assertFalse( proxyItem instanceof Book );
        Assert.assertTrue( proxyItem instanceof Item );
    }


    @Test
    public void 하이버네이트_언프록시_1 (){
        OrderItem orderItem = generateOrderItem();

        boolean isLoaded = emf.getPersistenceUnitUtil().isLoaded(orderItem.getItem());

        Item item = orderItem.getItem();
        Item unProxyitem = (Item) Hibernate.unproxy(item); //unproxy()

//        System.out.println("=============================================");
//        System.out.println("item 클래스 확인 = "+item.getClass());
//        System.out.println("unProxyitem 클래스 확인 = "+unProxyitem.getClass());
//        System.out.println("=============================================");
        
        if (unProxyitem instanceof Book) { // 전에 실패 했지만 지금은 성공
            System.out.println("=============================================");
            System.out.println("proxyitem instanceof Book");
            Book book = (Book) unProxyitem;
            System.out.println( "책 저자 = " + book.getAuthor());
        } else {
            System.out.println("부모타입프록시를 자식타입하고 instanceof 비교할 수 없습니다.");
        }

        Assert.assertTrue(item != unProxyitem); // 프록시 != 원본 엔티티 -> 참
    }

    @Test
    public void 기능을_위한_별도_인터페이스_제공_2 (){
        //생선된 주문아이템에는 Book 아이템이 들어가있다.
        OrderItem orderItem = generateOrderItem();
        orderItem.printItem();
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
