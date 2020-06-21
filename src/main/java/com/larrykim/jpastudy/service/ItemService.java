package com.larrykim.jpastudy.service;

import com.larrykim.jpastudy.dao.ItemRepository;
import com.larrykim.jpastudy.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    ItemRepository itemRepository;

    @Transactional
    public List<Item> get() {
        List<Item> items = itemRepository.findAll();

//        items.stream().forEach(item -> {
//            System.out.println("item.getPerson().getName() == " + item.getPerson().getName());
//        });

        return items;
    }
}
