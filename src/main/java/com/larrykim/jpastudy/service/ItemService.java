package com.larrykim.jpastudy.service;

import com.larrykim.jpastudy.domain.Item.Item;
import com.larrykim.jpastudy.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public void saveItem(Item item){
        itemRepository.save(item);
    }

    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    public Item fineOne(Long itemId){
        return itemRepository.findOne(itemId);
    }
}
