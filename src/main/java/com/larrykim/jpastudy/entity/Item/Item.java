package com.larrykim.jpastudy.entity.Item;

import com.larrykim.jpastudy.entity.TitleView;
import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@Entity
@RequiredArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
@ToString
public abstract class Item implements TitleView {
    @Id @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    private String name;

    public Item(String name) {
        this.name = name;
    }

}
