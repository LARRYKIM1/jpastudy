package com.larrykim.jpastudy.entity.Item;

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
    public String getTitle() {
        return "Movie 타이틀을 리턴합니다.";
    }
}