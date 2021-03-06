package com.larrykim.jpastudy.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ORDERS")
@Getter
@Setter
public class Order {
    @Id @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "DELIVERY_ID")
    private Delivery delivery;

    private Date orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    //==생성 메서드==//
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems) {
        Order order = new Order();
        order.setDelivery(delivery);
        order.setMember(member);
        for(OrderItem orderItem : orderItems){
            order.addOrderItem(orderItem);
        }
        order.setOrderDate(new Date());
        order.setStatus(OrderStatus.ORDER);
        return order;
    }

    /** 주문 취소 */
    public void cancel() {
        if ( this.delivery.getStatus() == DeliveryStatus.COMP){
            throw new RuntimeException("이미 배송완료된 상품입니다.");
        }

        setStatus(OrderStatus.CANCEL);

        for(OrderItem orderItem : orderItems){
            orderItem.cancel();
        }

    }

    public int getTotalPrice(){
        int totalPrice=0;
        for( OrderItem orderItem : orderItems ){
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }

    // 편의 메소드
    public void setMember(Member member){
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);
    }
}