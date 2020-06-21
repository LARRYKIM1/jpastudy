package com.larrykim.jpastudy.controller;

import com.larrykim.jpastudy.entity.Item;
import com.larrykim.jpastudy.service.ItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author Incheol Jung
 */
@Controller
public class ViewController {
    @Autowired
    ItemService itemService;

    @RequestMapping("/")
    public ModelAndView main() {
        ModelAndView mv = new ModelAndView();
        List<Item> items = itemService.get();

        System.out.println("==============================================");
        for(Item item : items){
            System.out.println(item.getPerson().getName());
        }
        System.out.println("==============================================");

        mv.addObject("items", items);
        mv.setViewName("index");
        return mv;
    }
}
