package com.larrykim.jpastudy.model;

import com.larrykim.jpastudy.entity.Person;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemResponse {
    private int id;
    private String itemName;
    private Person person;

    public ItemResponse(int id, String itemName) {
        this.id = id;
        this.itemName = itemName;
//        this.person = person;
    }

    //내가추가
    public ItemResponse(int id, String itemName, Person person) {
        this.id = id;
        this.itemName = itemName;
        this.person = person;
    }
}
