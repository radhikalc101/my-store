package org.launchcode.boot.store.controllers;

import org.launchcode.boot.store.models.data.ItemDao;
import org.launchcode.boot.store.models.forms.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.ArrayUtils;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
//@RequestMapping(value = "store")
@RequestMapping(value = "store/search")
public class SearchController {

    @Autowired
    private ItemDao itemDao;

    //@RequestMapping(value = "search/{keyword}", method = RequestMethod.GET)
    //public String findByKeyword(Model model, @PathVariable String keyword, HttpSession session)
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String findByKeyword(Model model, @RequestParam String keyword, HttpSession session){
        System.out.println(keyword);
        Iterable<Item> items = itemDao.findAll();
        List<Item> matchingItems = new ArrayList<>();
        if(keyword != null && !keyword.equals("")) {
            for (Item item : items) {
                if (item.getName().toLowerCase().contains(keyword.toLowerCase())) {
                    matchingItems.add(item);
                } else if (item.getCategory() != null && item.getCategory().getName().toLowerCase().contains(keyword.toLowerCase())) {
                    matchingItems.add(item);
                } else if (item.getBrand() != null && item.getBrand().getName().toLowerCase().contains(keyword.toLowerCase())) {
                    matchingItems.add(item);
                }
            }
            List<String> keywords = (List<String>) session.getAttribute("keywords");
            if(keywords == null){
                keywords = new ArrayList<>();
            }
            keywords.add(keyword);
            session.setAttribute("keywords", keywords);
        }
        model.addAttribute("items", matchingItems.isEmpty() ? items : matchingItems);
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("store", session.getAttribute("store"));
        model.addAttribute("keywords", session.getAttribute("keywords"));

        return "store/store_items";
    }

}
