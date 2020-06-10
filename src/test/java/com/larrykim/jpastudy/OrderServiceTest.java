package com.larrykim.jpastudy;

import com.larrykim.jpastudy.domain.*;
import com.larrykim.jpastudy.domain.Item.Book;
import com.larrykim.jpastudy.domain.Item.Item;
import com.larrykim.jpastudy.exception.NotEnoughStockException;
import com.larrykim.jpastudy.repository.OrderRepository;
import com.larrykim.jpastudy.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {
    @PersistenceContext
    EntityManager em;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderService orderService;


    @Test
    public void 상품주문() throws Exception{

        Member member = createMember();
        Item item = createBook("책",1000,10);

        int orderCount = 5;

        //1000원짜리 책을 5개 주문
        Long orderId = orderService.order(member.getId(),item.getId(),orderCount);

        Order order = orderRepository.findOne(orderId);

        assertEquals("상품주문시 상태는 ORDER", OrderStatus.ORDER,order.getStatus());
        assertEquals("주문한 상품수가 정확해야 한다.",1,order.getOrderItems().size());
        assertEquals("주문가격은 주문x수량 이다.",orderCount*1000,order.getTotalPrice());
        assertEquals("주문한 수량만큼 아이템이 준다.",5,item.getStockQuantity());

    }

    @Test(expected = NotEnoughStockException.class)
    public void 상품주문_재고수량초과() throws Exception{
        //given
        Member member = createMember();
        Item item = createBook("책",1000,10); //재고 10개 있음

        //when
        // 20개 초과수량
        orderService.order(member.getId(),item.getId(),20);

        //Then
        fail("재고 수량 부족 예외 발생");
    }

    @Test
    public void 주문취소(){
        Member member = createMember();
        Item item = createBook("책",1000,10);

        int orderCount = 5;

        Long orderId = orderService.order(member.getId(),item.getId(),orderCount);
        Order order = orderRepository.findOne(orderId);

        order.cancel();


        assertEquals("주문상태가 취소로 바뀐다",OrderStatus.CANCEL, order.getStatus());
        assertEquals("아이템이 5개에서 10개로 채워져야 한다.",10,item.getStockQuantity());

    }

    private Member createMember(){
        Member member = new Member();
        member.setName("멤버");
        member.setAddress(new Address("도시","동네","우편번호"));
        em.persist(member);
        return member;
    }

    private Book createBook(String name, int price, int stockQuantity){
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

}
