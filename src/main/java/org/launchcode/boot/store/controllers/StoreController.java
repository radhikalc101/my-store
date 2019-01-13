package org.launchcode.boot.store.controllers;


import org.launchcode.boot.store.models.data.addressDao;
import org.launchcode.boot.store.models.data.ownerAccountInfoDao;
import org.launchcode.boot.store.models.data.storeInfoDao;
import org.launchcode.boot.store.models.forms.Address;
import org.launchcode.boot.store.models.forms.OwnerAccountInfo;
import org.launchcode.boot.store.models.forms.StoreInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.sql.Timestamp;


@Controller
@RequestMapping(value = "store")
public class StoreController {

    @Autowired
    private storeInfoDao storeInfoDao;

    @Autowired
    private ownerAccountInfoDao ownerAccountInfoDao;

    @Autowired
    private addressDao addressDao;

    @RequestMapping(value = "")
    public String index(Model model, HttpSession session){
        if(session.getAttribute("user")!= null){
            return "redirect:/store/list";
        }
        return "redirect:/store/login";//rendering to template giving the path login.html file in the user directory

    }

    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String displayLoginForm(Model model){

        return "user/login";//rendering to template giving the path login.html file in the user directory

    }
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String processLoginForm(@RequestParam String email, @RequestParam String password, Model model, HttpSession session){// here the given param names should match in the form in the lable tag 'name' field
        OwnerAccountInfo ownerAccountInfo = ownerAccountInfoDao.findByEmail(email);//here first we are getting the email from the DB
//        System.out.println(ownerAccountInfo.toString());
        if(password.equals(ownerAccountInfo.getPassword())){ // here checking the given password matches to the pwd in the DB
            StoreInfo storeInfo = storeInfoDao.findByOwnerAccountInfo(ownerAccountInfo); // here getting the owner object Id ( all field details) from DB
            System.out.println(storeInfo.toString());
//            model.addAttribute("store",storeInfo); // here adding the StoreInfo object to the model or view
            session.setAttribute("user",email);
            session.setAttribute("store",storeInfo);
            return "redirect:/store/list";
        }else {
            return "redirect:/store/login";
        }

    }
    @RequestMapping(value = "logout")
    public String logout(Model model, HttpSession session){
        session.removeAttribute("user");
        return "redirect:/store/login";

    }
    @GetMapping(value = "signup")
    public String displaySignUpForm(Model model){
        StoreInfo store = new StoreInfo();

        Address storeAddress = new Address();
        store.setStoreAddress(storeAddress);// created new store object and new address object for the store and setting the new address object to the store

        OwnerAccountInfo owner = new OwnerAccountInfo();
        Address ownerAddress = new Address();
        owner.setOwnerAddress(ownerAddress);// created new owner object and new address object for the owner and setting the new address object to the owner

        store.setOwnerAccountInfo(owner);// setting the owner object with the address to the store object

        model.addAttribute("store", store);
        return "user/signup";//rendering to template giving the path signup.html file in the user directory
    }
//    @ResponseBody
    @PostMapping(value = "signup")
    public String processSignupForm(@ModelAttribute @Valid StoreInfo store, Model model, Errors errors, @RequestParam String confirmEmail, @RequestParam String confirmPassword, HttpSession session){
        if(errors.hasErrors()){
            return "user/signup";
        }
        String email = store.getOwnerAccountInfo().getEmail();
        String pwd = store.getOwnerAccountInfo().getPassword();
        if(email != null && confirmEmail !=null && email.equals(confirmEmail) && pwd != null && confirmPassword != null && pwd.equals(confirmPassword)){//checing the confirm Email and Password with the given Email and Password in the form

            Timestamp currentDateTime = new Timestamp(System.currentTimeMillis());// creating a new TimeStamp object for to use in our creationDateTime and updatedDateTime fields used in all tables
            store.setCreationDateTime(currentDateTime);// in the store object is already here so i'm setting the new TimeStamp object to the store object here
            store.setUpdatedDateTime(currentDateTime);
            store.getStoreAddress().setUpdatedDateTime(currentDateTime);// here we don't have the address object so that's y getting the address object first then setting the time object to the store object
            store.getStoreAddress().setCreationDateTime(currentDateTime);
            store.getOwnerAccountInfo().setCreationDateTime(currentDateTime);// here we don't have the OwnerAccountInfo object so that's y getOwnerAccountInfo  object first then setting the time object to the store object
            store.getOwnerAccountInfo().setUpdatedDateTime(currentDateTime);
            store.getOwnerAccountInfo().getOwnerAddress().setCreationDateTime(currentDateTime);// for this when the owner address was created to know first we get the ownerAcInfo obejct for that object we need address object so we get that too and finally we set the time to the store object
            store.getOwnerAccountInfo().getOwnerAddress().setUpdatedDateTime(currentDateTime);
//            System.out.println(store);
            addressDao.save(store.getStoreAddress());// witch ever object has "no" dependence objects that one we should add to the database first here they have the unique ids,saving the store address in the address table first
            addressDao.save(store.getOwnerAccountInfo().getOwnerAddress());// here saving the owner address in the address table
            ownerAccountInfoDao.save(store.getOwnerAccountInfo());// saving the owneracinfo object in the table
            storeInfoDao.save(store);// saving the store object in the database table finally along with the child objects

        }
        return "redirect:/store/login"; // once store is registered then the next page is store_items page we are rendering
    }

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String displayStoreItems(Model model, HttpSession session){
        if(session.getAttribute("user") != null && session.getAttribute("store") != null) {
            model.addAttribute("store", session.getAttribute("store"));
            //session.removeAttribute("store");
            model.addAttribute("user", session.getAttribute("user"));
            return "store/store_items";//rendering to template giving the path login.html file in the user directory
        } else {
            return "redirect:/store/logout";
        }

    }

}
