package org.launchcode.boot.store.controllers;

import org.launchcode.boot.store.models.Item;
import org.launchcode.boot.store.models.StoreInfo;
import org.launchcode.boot.store.services.StoreRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "store")
public class SearchController {

    @Autowired
    private StoreRestService restService;


    @RequestMapping(value = "search/{keyword}", method = RequestMethod.GET)
    public String findByKeyword(Model model, @PathVariable String keyword, HttpSession session){

        List<String> keywords = (List<String>) session.getAttribute("keywords");
        if (keywords == null) {
            keywords = new ArrayList<>();
        }
        if(keyword != null && !keyword.trim().equals("")) {
            keywords.add(keyword.toLowerCase());
        }
        session.setAttribute("keywords", keywords);// setting the given keyword to session of list keywords
        return this.search(keywords, model, session);
    }
    @RequestMapping(value = "search", method = RequestMethod.GET)
    public String findAll(Model model, HttpSession session){
        return this.search(new ArrayList<>(), model, session);
    }

    @RequestMapping(value = "search/clear/{keyword}", method = RequestMethod.GET)
    public String clearKeyword(Model model, HttpSession session, @PathVariable String keyword){

        List<String> keywords = (List<String>) session.getAttribute("keywords");
        keywords.remove(keyword);
        session.setAttribute("keywords",keywords);
        return this.search(keywords, model, session);

    }

    private String search(List<String> keywords, Model model, HttpSession session){
        StoreInfo storeInfo = (StoreInfo) session.getAttribute("store");
        List<Item> items = restService.findItemsByStore(storeInfo.getId());
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
        model.addAttribute("items", matchingItems);//ternary if condition (if thr is no search keyword then list all items on display)

        model.addAttribute("keywords", session.getAttribute("keywords"));
        return "store/cards :: itemCards";
    }

    private boolean isKeywordExist(String name, List<String> keywords){
        boolean found = keywords.isEmpty();
        for(String keyword : keywords){
            if(name.toLowerCase().contains(keyword)){
                found = true;
                break;
            }
        }
        return found;
    }

}
