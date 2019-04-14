package org.launchcode.boot.store.controllers;


//import com.fasterxml.jackson.core.JsonFactory;
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.MappingIterator;
//import com.fasterxml.jackson.databind.ObjectMapper;
import org.launchcode.boot.store.models.data.*;
import org.launchcode.boot.store.models.forms.*;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStream;
//import java.nio.file.Paths;
import java.sql.Timestamp;
//import java.util.List;


@Controller
@RequestMapping(value = "store")
public class StoreController {

    @Autowired
    private StoreInfoDao storeInfoDao;

    @Autowired
    private OwnerAccountInfoDao ownerAccountInfoDao;

    @Autowired
    private AddressDao addressDao;

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private DBFileDao fileDao;

//    private State[] states;

    @RequestMapping(value = "maps", method =RequestMethod.GET)
        public String mapsDisplay (Model model){
        return "store/google_maps";

        }


    @GetMapping(value = "upload")
    public String uploadImage(Model model){
        return "upload/images";
    }

    @RequestMapping(value = "")
    public String index(Model model, HttpSession session){
        if(session.getAttribute("user")!= null){
            return "redirect:/store/list";
        }
        return "redirect:/store/login";//rendering to template giving the path login.html file in the user directory

    }

    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String displayLoginForm(Model model, HttpSession session){
        model.addAttribute("errorMessage",null);
        this.clear(session);
        return "user/login";//rendering to template giving the path login.html file in the user directory

    }
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String processLoginForm(@RequestParam String email, @RequestParam String password, Model model, HttpSession session){// here the given param names should match in the form in the lable tag 'name' field
        OwnerAccountInfo ownerAccountInfo = ownerAccountInfoDao.findByEmail(email);//here first we are getting the email from the DB
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String hashedPassword = passwordEncoder.encode(password);
        if(ownerAccountInfo != null && password.equals(ownerAccountInfo.getPassword())){ // here checking the given password matches to the pwd in the DB
            StoreInfo storeInfo = storeInfoDao.findByOwnerAccountInfo(ownerAccountInfo); // here getting the owner object Id ( all field details) from DB
            String userFullName = ownerAccountInfo.getFirstName()+" "+ ownerAccountInfo.getLastName();//getting the user full name to print on view page list.
            session.setAttribute("user",userFullName);
            session.setAttribute("email",email);
            session.setAttribute("store",storeInfo);
            return "redirect:/store/list";
        }else {
            model.addAttribute("errorMessage","Invalid User Name / Password");
            return "user/login";//we need proper msg email and the password doesn't mach
        }

    }
    private void clear(HttpSession session){
        session.removeAttribute("user");
        session.removeAttribute("store");
        session.removeAttribute("email");
        session.removeAttribute("keywords");
    }
    @RequestMapping(value = "logout")
    public String logout(Model model, HttpSession session){
        this.clear(session);
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
//        model.addAttribute("states", this.getStates());
        model.addAttribute("store", store);
        return "user/signup";//rendering to template giving the path signup.html file in the user directory
    }

//    private State[] getStates(){
//        if(this.states == null || this.states.length == 0) {
//            ObjectMapper objectMapper = new ObjectMapper();
//            try {
//                this.states = objectMapper.readValue(new File(Paths.get("States.json").toUri()), State[].class);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return this.states;
//
//    }
    @PostMapping(value = "signup")
    public String processSignupForm(@ModelAttribute @Valid StoreInfo store, Model model, Errors errors, @RequestParam String confirmEmail, @RequestParam String confirmPassword, HttpSession session){
        if(errors.hasErrors()){
            return "user/signup";
        }
        String email = store.getOwnerAccountInfo().getEmail();
        String pwd = store.getOwnerAccountInfo().getPassword();
        if(email != null && confirmEmail !=null && email.equals(confirmEmail) && pwd != null && confirmPassword != null && pwd.equals(confirmPassword)){//checing the confirm Email and Password with the given Email and Password in the form
//            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//            String hashedPassword = passwordEncoder.encode(pwd);
//            store.getOwnerAccountInfo().setPassword(hashedPassword);
            Timestamp currentDateTime = new Timestamp(System.currentTimeMillis());// creating a new TimeStamp object for to use in our creationDateTime and updatedDateTime fields used in all tables
            store.setCreationDateTime(currentDateTime);// in the store object is already here so i'm setting the new TimeStamp object to the store object here
            store.setUpdatedDateTime(currentDateTime);
            store.getStoreAddress().setUpdatedDateTime(currentDateTime);// here we don't have the address object so that's y getting the address object first then setting the time object to the store object
            store.getStoreAddress().setCreationDateTime(currentDateTime);
            store.getOwnerAccountInfo().setCreationDateTime(currentDateTime);// here we don't have the OwnerAccountInfo object so that's y getOwnerAccountInfo  object first then setting the time object to the store object
            store.getOwnerAccountInfo().setUpdatedDateTime(currentDateTime);
            store.getOwnerAccountInfo().getOwnerAddress().setCreationDateTime(currentDateTime);// for this when the owner address was created to know first we get the ownerAcInfo obejct for that object we need address object so we get that too and finally we set the time to the store object
            store.getOwnerAccountInfo().getOwnerAddress().setUpdatedDateTime(currentDateTime);
           System.out.println(store.getStoreAddress());
            addressDao.save(store.getStoreAddress());// witch ever object has "no" dependence objects that one we should add to the database first here they have the unique ids,saving the store address in the address table first
            System.out.println(store.getOwnerAccountInfo().getOwnerAddress());
            addressDao.save(store.getOwnerAccountInfo().getOwnerAddress());// here saving the owner address in the address table
            ownerAccountInfoDao.save(store.getOwnerAccountInfo());// saving the owneracinfo object in the table
            storeInfoDao.save(store);// saving the store object in the database table finally along with the child objects

        }
        return "redirect:/store/login"; // once store is registered then the next page is store_items page we are rendering
    }

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String displayStoreItems(Model model, HttpSession session){
        if(session.getAttribute("email") != null && session.getAttribute("store") != null) {
          // here getting the owner object Id ( all field details) from DB
            StoreInfo storeInfo = (StoreInfo) session.getAttribute("store");
            model.addAttribute("items",itemDao.findByStoreInfo(storeInfo));
            model.addAttribute("user",session.getAttribute("user") );
            model.addAttribute("store", storeInfo);
            model.addAttribute("keyword", "");
            return "store/store_items";//rendering to template giving the path store_items.html file in the user directory
        } else {
            return "redirect:/store/logout";
        }

    }

    @ResponseBody
    @RequestMapping(value = "data", method = RequestMethod.GET, produces = "application/json")
    public Iterable<StoreInfo> data() {
        return storeInfoDao.findAll();
    }

}
