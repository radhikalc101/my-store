package org.launchcode.boot.store.controllers;

import org.launchcode.boot.store.models.StoreInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "store/user")
public class UserController {

    @RequestMapping(value = "profile", method = RequestMethod.GET)
    public String displayProfile(HttpSession session,Model model) {
        StoreInfo store = (StoreInfo) session.getAttribute("store");
        model.addAttribute("store",store);// we have to pass attributename and value
        return "user/profile";
    }
}
