package org.launchcode.boot.store.controllers;

import org.launchcode.boot.store.models.data.BrandDao;
import org.launchcode.boot.store.models.forms.Brand;
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
@RequestMapping(value = "store/brand" )
public class BrandController  {

    @Autowired
    private BrandDao brandDao;

    @GetMapping(value = "add")
    public String addBrandForm(Model model){
        model.addAttribute("brand",new Brand());// created new Brand object
        model.addAttribute("brands",brandDao.findAll());// get all the brand names for DB
        return "store/add_brand";
    }
    @PostMapping(value = "add")
    public String processBrandFrom(@ModelAttribute @Valid Brand brandName, Errors errors, Model model){
        if(errors.hasErrors()){
            return "store/add_brand";// giving the path of the html form so add_brand.html file is in the store folder
        }
        brandDao.save(brandName);// save the given brand name in the DB
        return "redirect:/store/brand/add";// redirecting to requests first it will go to top level request then come to the add request.
    }
}
