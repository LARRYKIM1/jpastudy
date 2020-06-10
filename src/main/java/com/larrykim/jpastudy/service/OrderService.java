package com.larrykim.jpastudy.service;

import com.larrykim.jpastudy.domain.*;
import com.larrykim.jpastudy.domain.Item.Item;
import com.larrykim.jpastudy.repository.MemberRepository;
import com.larrykim.jpastudy.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final ItemService itemService;

    // 주문
    public Long order(Long memberId, Long itemId, int count){

        Member member = memberRepository.findOne(memberId);
        Item item = itemService.findOne(itemId);

        Delivery delivery = new Delivery(member.getAddress());

        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        Order order = Order.createOrder(member, delivery, orderItem);

        orderRepository.save(order);

        return order.getId();
    }

    // 주문 취소
    public void cancelOrder(Long orderId){
        Order order = orderRepository.findOne(orderId);
        order.cancel();
        //취소내역도 남아잇으니... 멤버는 없애지 않아도 되나봐...
    }

    // 주문 검색
    public List<Order> findOrders(OrderSearch orderSearch){
        return orderRepository.findAll(orderSearch);
    }
}
