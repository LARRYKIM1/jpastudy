package com.larrykim.jpastudy.entity.Item;

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
    public String getTitle() {
        return "Album 타이틀을 리턴합니다.";
    }
}