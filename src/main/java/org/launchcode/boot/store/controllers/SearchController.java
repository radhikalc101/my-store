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

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "store/search")
public class SearchController {

    @Autowired
    private ItemDao itemDao;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String findByKeyword(Model model, @RequestParam String keyword, HttpSession session){

        List<String> keywords = (List<String>) session.getAttribute("keywords");// getting the given keyword and putting it in the session
        if (keywords == null) {
            keywords = new ArrayList<>();//creating a new list of keywords array
        }
        if(keyword != null) {
            keywords.add(keyword.toLowerCase());// adding the given keyword to the list
        }
        session.setAttribute("keywords", keywords);// setting the given keyword to session of list keywords

//        Iterable<Item> items = itemDao.findAll();
//        List<Item> matchingItems = new ArrayList<>();
//        if(keyword != null && !keyword.equals("")) {
//            for (Item item : items) {
//                if (item.getName().toLowerCase().contains(keyword.toLowerCase())) {
//                    matchingItems.add(item);
//                } else if (item.getCategory() != null && item.getCategory().getName().toLowerCase().contains(keyword.toLowerCase())) {
//                    matchingItems.add(item);
//                } else if (item.getBrand() != null && item.getBrand().getName().toLowerCase().contains(keyword.toLowerCase())) {
//                    matchingItems.add(item);
//                }
//            }
//
//        }
//        model.addAttribute("items", matchingItems.isEmpty() ? items : matchingItems);
//        model.addAttribute("user", session.getAttribute("user"));
//        model.addAttribute("store", session.getAttribute("store"));
//        model.addAttribute("keywords", session.getAttribute("keywords"));
//
//        return "store/store_items";
        return this.search(keywords, model, session);// calling the search method in the same call we are using this.
    }
    @RequestMapping(value = "clear/{keyword}", method = RequestMethod.GET)
    public String clearKeyword(Model model, HttpSession session, @PathVariable String keyword){

        List<String> keywords = (List<String>) session.getAttribute("keywords");// getting all the keywords from the session
        keywords.remove(keyword);// removing given keyword from the list of keywords and adding(setting) the rest of keywords list to session
        session.setAttribute("keywords",keywords);
        return this.search(keywords, model, session);// method calling

    }

    private String search(List<String> keywords, Model model, HttpSession session){
        Iterable<Item> items = itemDao.findAll();
        List<Item> matchingItems = new ArrayList<>();
        for (Item item : items) {
            if (isKeywordExist(item.getName(), keywords)) {
                matchingItems.add(item);
            } else if (item.getCategory() != null && isKeywordExist(item.getCategory().getName(), keywords)) {
                matchingItems.add(item);
            } else if (item.getBrand() != null && isKeywordExist(item.getBrand().getName(), keywords)) {
                matchingItems.add(item);
            }
        }

        model.addAttribute("items", matchingItems.isEmpty() ? items : matchingItems);//ternary if condition (if thr is no search keyword then list all items on display)
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("store", session.getAttribute("store"));
        model.addAttribute("keywords", session.getAttribute("keywords"));

        return "store/store_items";
    }

    private boolean isKeywordExist(String name, List<String> keywords){
        boolean found = false;
        for(String keyword : keywords){
            if(name.toLowerCase().contains(keyword)){
                found = true;
                break;
            }
        }
        return found;
    }

}
