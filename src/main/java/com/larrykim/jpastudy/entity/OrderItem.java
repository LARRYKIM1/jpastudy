package com.larrykim.jpastudy.entity;

import com.larrykim.jpastudy.entity.Item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ORDER_ITEM")
@Getter
@Setter
public class OrderItem {
    @Id @GeneratedValue
    @Column(name = "ORDER_ITEM_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    public void printItem() {
        System.out.println("OrderItem의 printItem() 메소드를 호출합니다.");
        System.out.println("==============================");
        System.out.println("TITLE="+item.getTitle());
        System.out.println("==============================");
    }
}