package com.larrykim.jpastudy.entity.Item;

import com.larrykim.jpastudy.common.Visitor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@DiscriminatorValue("B")
public class Book extends Item{
    private String author;
    private String isbn;

    @Builder
    private Book(String name, String author){
        super(name);
        this.author = author;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
