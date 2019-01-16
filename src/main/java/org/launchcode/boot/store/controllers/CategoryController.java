package org.launchcode.boot.store.controllers;

import org.launchcode.boot.store.models.data.CategoryDao;
import org.launchcode.boot.store.models.forms.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "store/category")
public class CategoryController {

    @Autowired
    private CategoryDao categoryDao;

    @GetMapping(value = "add")
    public String addCategoryForm(Model model){
        model.addAttribute("category", new Category());//this is the object name
        model.addAttribute("categories",categoryDao.findAll());//getting all the items name form the DB
        return "category/add_category";// render template to the file giving the path
    }
    @PostMapping(value = "add")
    public String processAddCategoryForm(@ModelAttribute @Valid Category category, Errors errors, Model model){
        if(errors.hasErrors()){
            return "category/add_category"; // if it has errors it is returning to the folder of category in that add_category.html form view page
        }
        categoryDao.save(category);// save the given category name in the DB
        return "redirect:/store/category/add"; // displaying the category  name in the same add page
    }

}
