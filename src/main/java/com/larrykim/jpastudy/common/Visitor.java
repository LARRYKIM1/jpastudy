package com.larrykim.jpastudy.common;

import com.larrykim.jpastudy.entity.Item.Album;
import com.larrykim.jpastudy.entity.Item.Book;
import com.larrykim.jpastudy.entity.Item.Movie;

public interface Visitor {
    void visit(Book book);
    void visit(Movie movie);
    void visit(Album album);
}
