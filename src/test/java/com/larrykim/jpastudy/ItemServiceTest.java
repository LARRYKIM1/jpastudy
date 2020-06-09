package com.larrykim.jpastudy;

import com.larrykim.jpastudy.domain.Item.Book;
import com.larrykim.jpastudy.domain.Item.Item;
import com.larrykim.jpastudy.repository.ItemRepository;
import com.larrykim.jpastudy.service.ItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ItemServiceTest {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ItemService itemService;

    @Test
    public void 아이템_등록() throws Exception{
        //Given
        Item book = new Book();
        book.setName("책1");

        //When
        itemService.saveItem(book);

        //Then
        assertEquals(1, itemRepository.findAll().size());
    }

}
