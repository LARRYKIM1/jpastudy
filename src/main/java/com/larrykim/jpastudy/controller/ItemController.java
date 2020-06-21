package com.larrykim.jpastudy.controller;

import com.larrykim.jpastudy.entity.Item;
import com.larrykim.jpastudy.model.ItemResponse;
import com.larrykim.jpastudy.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/items", produces = "application/json")
public class ItemController {

    @Autowired
    ItemService itemService;

    @GetMapping(value = "")
    public List<ItemResponse> get() {
        List<Item> items =  itemService.get();
        Item firstItem = items.get(0);

//        System.out.println("==============================================");
//        System.out.println("firstItem.getPerson().getName() == " + firstItem.getPerson().getName());

        System.out.println("==============================================");
        for(Item item: items){
            System.out.println("item.getItemName = "+item.getItemName()+", item.getPerson().getName() = "+item.getPerson().getName());
        }
        System.out.println("==============================================");

        return items.stream()
                    .map(item -> new ItemResponse(item.getId(), item.getItemName(), item.getPerson()))
                    .collect(Collectors.toList());
    }

    @GetMapping(value = "/test")
    public List<Item> get2() {
        List<Item> items =  itemService.get();
        return items;
    }

}
