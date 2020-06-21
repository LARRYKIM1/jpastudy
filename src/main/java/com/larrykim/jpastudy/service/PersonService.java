package com.larrykim.jpastudy.service;

import com.larrykim.jpastudy.dao.ItemRepository;
import com.larrykim.jpastudy.dao.PersonRepository;
import com.larrykim.jpastudy.entity.Item;
import com.larrykim.jpastudy.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {
    @Autowired
    PersonRepository personRepository;

    @Autowired
    ItemRepository itemRepository;

    public List<Person> get() {
        return personRepository.findAll();
    }

    public Person save(String name) {
        Person person = new Person(name);
        person = personRepository.save(person);

        List<Item> items = new ArrayList<>();
        for(int i=0; i<10; i++){
            Item item = new Item("item" + i);
            item.setPerson(person);
            items.add(item);
        }
        itemRepository.saveAll(items);

        return person;
    }
}
