package org.launchcode.boot.store.controllers;

import org.launchcode.boot.store.models.data.BrandDao;
import org.launchcode.boot.store.models.data.CategoryDao;
import org.launchcode.boot.store.models.data.ItemDao;

import org.launchcode.boot.store.models.forms.Brand;
import org.launchcode.boot.store.models.forms.Category;
import org.launchcode.boot.store.models.forms.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "store/item")
public class ItemController {

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private BrandDao brandDao;

    @RequestMapping(value = "add",method = RequestMethod.GET)
    public String addItemForm(Model model){
        model.addAttribute(new Item());//create new item object and add it to the model
        model.addAttribute("categories",categoryDao.findAll());//get all the category names DB
        model.addAttribute("brands",brandDao.findAll());//get all the brand names form DB
        return "store/add_item";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processOfAddItemForm(@ModelAttribute @Valid Item item, Errors errors,
                                       @RequestParam int categoryId,@RequestParam int brandId, Model model){
        if(errors.hasErrors()){
            model.addAttribute("categories",categoryDao.findAll());//if errors occurs again display the list of category names for again new request
            model.addAttribute("brands",brandDao.findAll());//same for brand names too
            return "store/add_item";
        }
        System.out.println(categoryId);
        System.out.println(brandId);
        if(categoryId != 0) {
            Category selectedCatName = categoryDao.findById(categoryId).get();// the findById method is optional thats y we use .get() to the id
            item.setCategory(selectedCatName);
        }
        if(brandId != 0) { // checking item doesn't have a brand it will be none
            Brand selectedBrandName = brandDao.findById(brandId).get();
            item.setBrand(selectedBrandName);
        }
        itemDao.save(item);
        return "redirect:/store/list";
    }

    // edit the item details by creating blow handler

    @GetMapping(value = "edit/{id}")
    public String editFormDisplay(Model model, @PathVariable int id){
        Item itemObject = itemDao.findById(id).get();
        model.addAttribute("item",itemObject);
        model.addAttribute("categories",categoryDao.findAll());//get all the category names DB
        model.addAttribute("brands",brandDao.findAll());//get all the brand names form DB
        return "store/edit_item";

    }

    @PostMapping(value = "edit")
    public String editFormProcess(@ModelAttribute @Validated Item item, Errors errors, Model model){
        if(errors.hasErrors()){
            return "store/edit_item";
        }
        System.out.println(item.toString());
        itemDao.save(item);

        return "redirect:/store/list";

    }


}
