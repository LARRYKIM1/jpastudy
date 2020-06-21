package com.larrykim.jpastudy;

import com.larrykim.jpastudy.entity.Item.Book;
import com.larrykim.jpastudy.entity.Item.Item;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ParentChildProxyTest {
    @PersistenceContext
    EntityManager em;

    @Test
    public void 부모타입으로_프록시조회 () {
        Book savedBook = new Book();
        savedBook.setName("jpabook");
        savedBook.setAuthor("kim");
        em.persist(savedBook);
        em.flush();
        em.clear();

        // 프록시를 부모 타입으로 조회
        Item proxyItem = em.getReference(Item.class, savedBook.getId());

        if (proxyItem instanceof Book) { // false
            System.out.println("proxyitem instanceof Book");
            Book book = (Book) proxyItem;
            System.out.println("책 저자 = " + book.getAuthor());
        } else {
            System.out.println("부모타입프록시를 자식타입하고 instanceof 비교할 수 없습니다.");
        }

        Assert.assertFalse( proxyItem.getClass() == Book.class );
        Assert.assertFalse( proxyItem instanceof Book );
        Assert.assertTrue( proxyItem instanceof Item );
    }

}
