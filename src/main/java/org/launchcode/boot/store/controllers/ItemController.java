package org.launchcode.boot.store.controllers;

import org.launchcode.boot.store.models.*;
import org.launchcode.boot.store.repositories.ItemRepository;
import org.launchcode.boot.store.models.ImageFile;
import org.launchcode.boot.store.services.StoreRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;


@Controller
@RequestMapping(value = "store/item")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private StoreRestService restService;

    @RequestMapping(value = "/imageDisplay", method = RequestMethod.GET)
    public void showImage(@RequestParam("id") Integer imageId, HttpServletResponse response) {
        if(imageId != 0) {
            ImageFile imageFile = restService.getImageFile(imageId);
            response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
            try {
                response.getOutputStream().write(imageFile.getData());
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
            item.setImageFile(new ImageFile());
            model.addAttribute(item);
            model.addAttribute("categories", restService.getAllCategories());
            model.addAttribute("brands", restService.getAllBrands());
            return "store/add_item";
        }
        return "redirect:/store/login";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processOfAddItemForm(@ModelAttribute @Valid Item item, Errors errors,
                                       @RequestParam int categoryId, @RequestParam int brandId, @RequestParam int imageId, Model model, HttpSession session){

        if(errors.hasErrors()){
            model.addAttribute("categories", restService.getAllCategories());
            model.addAttribute("brands", restService.getAllBrands());
            return "store/add_item";
        }
        if(categoryId != 0) {
            Category selectedCatName = restService.findCategoryById(categoryId);
            item.setCategory(selectedCatName);
        }
        if(brandId != 0) {
            Brand selectedBrandName = restService.findBrandById(brandId);
            item.setBrand(selectedBrandName);
        }
        if(imageId != 0) {
            ImageFile imageFile = restService.getImageFile(imageId);
            item.setImageFile(imageFile);
        }
        StoreInfo storeInfo = (StoreInfo) session.getAttribute("store");
        item.setStoreInfo(storeInfo);
        restService.saveItem(item);
        return "redirect:/store/list";
    }

    // edit the item details by creating blow handler

    @GetMapping(value = "edit/{id}")
    public String editFormDisplay(Model model, @PathVariable int id){
        Item itemObject = restService.findItemById(id);
        model.addAttribute("item",itemObject);
        model.addAttribute("categories", restService.getAllCategories());
        model.addAttribute("brands", restService.getAllBrands());
        return "store/edit_item";

    }

    @PostMapping(value = "edit")
    public String editFormProcess(@ModelAttribute @Validated Item item, @RequestParam int imageId, Errors errors, Model model, HttpSession session){
        if(errors.hasErrors()){
            return "store/edit_item";
        }
        ImageFile imageFile = restService.getImageFile(imageId);
        item.setImageFile(imageFile);

        StoreInfo storeInfo = (StoreInfo) session.getAttribute("store");
        item.setStoreInfo(storeInfo);
        restService.saveItem(item);
        return "redirect:/store/list";
    }
    // deleting the item form list of items using itemId
    @RequestMapping(value = "delete/{itemId}", method = RequestMethod.GET)
    public String deleteItem(Model model,@PathVariable int itemId, HttpSession session){ ;
        restService.deleteItemById(itemId);
        if(session.getAttribute("email") != null && session.getAttribute("store") != null) {
            StoreInfo storeInfo = (StoreInfo) session.getAttribute("store");
            model.addAttribute("items", restService.findItemsByStore(storeInfo.getId()));
            model.addAttribute("keywords", session.getAttribute("keywords"));
            return "store/cards :: itemCards";
        } else {
            return "redirect:/store/logout";
        }

    }

}
