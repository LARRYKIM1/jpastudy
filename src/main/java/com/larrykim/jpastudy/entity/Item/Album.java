package com.larrykim.jpastudy.entity.Item;

import com.larrykim.jpastudy.common.Visitor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter @Setter
@Entity
@DiscriminatorValue("A")
public class Album extends Item {
    private String artist;
    private String etc;


    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}