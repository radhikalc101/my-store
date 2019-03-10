package org.launchcode.boot.store.controllers;

import org.launchcode.boot.store.models.data.ItemDao;
import org.launchcode.boot.store.models.forms.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "store/item" )
public class ItemRestController {

    @Autowired
    private ItemDao itemDao;

    @RequestMapping(value = "publish/{id}", method = RequestMethod.GET)
    public int publishItem(Model model, @PathVariable int id){
        Item item = itemDao.findById(id).get();
        item.setPublished(!item.isPublished());
        itemDao.save(item);
        return id;
    }
}
