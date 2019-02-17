package org.launchcode.boot.store.controllers;

import org.launchcode.boot.store.models.Category;
import org.launchcode.boot.store.services.StoreRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "store/category")
public class CategoryController {

    @Autowired
    private StoreRestService restService;

    @GetMapping(value = "add")
    public String addCategoryForm(Model model, HttpSession session){
        if(session.getAttribute("user")!= null){
            model.addAttribute("category", new Category());
            model.addAttribute("categories", restService.getAllCategories());
            return "category/add_category";
        }

        return "redirect:/store/login";
    }
    @PostMapping(value = "add")
    public String processAddCategoryForm(@ModelAttribute @Valid Category category, Errors errors){
        if(errors.hasErrors()){
            return "category/add_category";
        }
        restService.saveCategory(category);
        return "redirect:/store/category/add";
    }

}
