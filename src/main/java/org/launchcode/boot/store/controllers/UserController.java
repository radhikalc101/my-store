package org.launchcode.boot.store.controllers;

import org.launchcode.boot.store.models.data.AddressDao;
import org.launchcode.boot.store.models.data.OwnerAccountInfoDao;
import org.launchcode.boot.store.models.data.StoreInfoDao;
import org.launchcode.boot.store.models.forms.StoreInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "store/user")
public class UserController {
    @Autowired
    private StoreInfoDao storeInfoDao;

    @Autowired
    private OwnerAccountInfoDao ownerAccountInfoDao;

    @Autowired
    private AddressDao addressDao;


    @RequestMapping(value = "profile", method = RequestMethod.GET)
    public String displayProfile(HttpSession session,Model model) {
        StoreInfo store = (StoreInfo) session.getAttribute("store");
//        System.out.println(store.toString());
        model.addAttribute("store",store);// we have to pass attributename and value
        return "user/profile";
    }
}
