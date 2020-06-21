package com.larrykim.jpastudy.entity.Item;

import com.larrykim.jpastudy.common.Visitor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Setter
@Getter
@Entity
@DiscriminatorValue("M")
public class Movie extends Item{
    private String director;
    private String actor;

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}