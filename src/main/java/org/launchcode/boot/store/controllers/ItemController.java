package org.launchcode.boot.store.controllers;

import org.launchcode.boot.store.models.data.BrandDao;
import org.launchcode.boot.store.models.data.CategoryDao;
import org.launchcode.boot.store.models.data.DBFileDao;
import org.launchcode.boot.store.models.data.ItemDao;
import org.launchcode.boot.store.models.forms.Brand;
import org.launchcode.boot.store.models.forms.Category;
import org.launchcode.boot.store.models.forms.DBFile;
import org.launchcode.boot.store.models.forms.Item;
import org.launchcode.boot.store.models.forms.StoreInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


@Controller
@RequestMapping(value = "store/item")
public class ItemController {

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private BrandDao brandDao;

    @Autowired
    private DBFileDao fileDao;

    @RequestMapping(value = "/imageDisplay", method = RequestMethod.GET)
    public void showImage(@RequestParam("id") Integer imageId, HttpServletResponse response) {
        if(imageId != 0) {
            DBFile image = fileDao.findById(imageId).get();
            response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
            try {
                response.getOutputStream().write(image.getData());
                response.getOutputStream().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @RequestMapping(value = "add",method = RequestMethod.GET)
    public String addItemForm(Model model,HttpSession session){
        if(session.getAttribute("user")!= null){
            Item item = new Item();
            item.setImage(new DBFile());
            model.addAttribute(item);
            model.addAttribute("categories",categoryDao.findAll());
            model.addAttribute("brands",brandDao.findAll());
            return "store/add_item";
        }
        return "redirect:/store/login";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processOfAddItemForm(@ModelAttribute @Valid Item item, Errors errors,
                                       @RequestParam int categoryId, @RequestParam int brandId, @RequestParam int imageId, Model model, HttpSession session){
        if(errors.hasErrors()){
            model.addAttribute("categories",categoryDao.findAll());
            model.addAttribute("brands",brandDao.findAll());
            return "store/add_item";
        }
        if(categoryId != 0) {
            Category selectedCatName = categoryDao.findById(categoryId).get();
            item.setCategory(selectedCatName);
        }
        if(brandId != 0) {
            Brand selectedBrandName = brandDao.findById(brandId).get();
            item.setBrand(selectedBrandName);
        }
        if(imageId != 0) {
            DBFile image = fileDao.findById(imageId).get();
            item.setImage(image);
        }
        StoreInfo storeInfo = (StoreInfo) session.getAttribute("store");
        item.setStoreInfo(storeInfo);
        itemDao.save(item);
        return "redirect:/store/list";
    }

    // edit the item details by creating blow handler

    @GetMapping(value = "edit/{id}")
    public String editFormDisplay(Model model, @PathVariable int id){
        Item itemObject = itemDao.findById(id).get();
        model.addAttribute("item",itemObject);
        model.addAttribute("categories",categoryDao.findAll());
        model.addAttribute("brands",brandDao.findAll());
        return "store/edit_item";

    }

    @PostMapping(value = "edit")
    public String editFormProcess(@ModelAttribute @Validated Item item, @RequestParam int imageId, Errors errors, Model model, HttpSession session){
        if(errors.hasErrors()){
            return "store/edit_item";
        }

        DBFile image = fileDao.findById(imageId).get();
        item.setImage(image);

        StoreInfo storeInfo = (StoreInfo) session.getAttribute("store");
        item.setStoreInfo(storeInfo);
        itemDao.save(item);

        return "redirect:/store/list";
    }
    // deleting the item form list of items using itemId
    @RequestMapping(value = "delete/{itemId}", method = RequestMethod.GET)
    public String deleteItem(Model model,@PathVariable int itemId, HttpSession session){
        itemDao.deleteById(itemId);
        if(session.getAttribute("email") != null && session.getAttribute("store") != null) {
            StoreInfo storeInfo = (StoreInfo) session.getAttribute("store");
            model.addAttribute("items",itemDao.findByStoreInfo(storeInfo));
//            model.addAttribute("user",session.getAttribute("user") );
//            model.addAttribute("store", storeInfo);
//            model.addAttribute("keyword", "");
            model.addAttribute("keywords", session.getAttribute("keywords"));
            return "store/cards :: itemCards";
        } else {
            return "redirect:/store/logout";
        }

    }

}
