package com.larrykim.jpastudy.domain.Item;

import com.larrykim.jpastudy.domain.Category;
import com.larrykim.jpastudy.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
public abstract class Item {
    @Id @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    private String name;
    private int price;
    private  int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    public void addStock(int quantity){
        this.stockQuantity+=quantity;
    }
    public void removeStock(int quantity) {
        int resetStock = this.stockQuantity-quantity;
        if(resetStock < 0){
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = resetStock;
    }

}
