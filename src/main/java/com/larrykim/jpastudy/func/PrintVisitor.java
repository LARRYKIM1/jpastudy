package com.larrykim.jpastudy.func;

import com.larrykim.jpastudy.common.Visitor;
import com.larrykim.jpastudy.entity.Item.Album;
import com.larrykim.jpastudy.entity.Item.Book;
import com.larrykim.jpastudy.entity.Item.Movie;

public class PrintVisitor implements Visitor {
    @Override
    public void visit(Book book) {
        System.out.println("2. 두번째 ");
        System.out.println("Book 을 프린트합니다.");
    }

    @Override
    public void visit(Movie movie) {
        System.out.println("Movie 을 프린트합니다.");
    }

    @Override
    public void visit(Album album) {
        System.out.println("Album 을 프린트합니다.");
    }
}
