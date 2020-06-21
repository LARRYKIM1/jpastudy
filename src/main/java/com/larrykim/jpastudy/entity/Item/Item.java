package com.larrykim.jpastudy.entity.Item;

import com.larrykim.jpastudy.common.Visitor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


@Getter
@Setter
@Entity
@RequiredArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
@ToString
public abstract class Item {
    @Id @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    private String name;

    public Item(String name) {
        this.name = name;
    }

    public abstract void accept(Visitor visitor);

}
