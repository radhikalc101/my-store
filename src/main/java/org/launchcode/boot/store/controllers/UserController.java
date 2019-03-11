package org.launchcode.boot.store.controllers;

import org.launchcode.boot.store.models.data.OwnerAccountInfoDao;
import org.launchcode.boot.store.models.forms.OwnerAccountInfo;
import org.launchcode.boot.store.models.forms.StoreInfo;
import org.launchcode.boot.store.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;

import org.springframework.mail.SimpleMailMessage;

@Controller
@RequestMapping(value = "store/user")
public class UserController {

    private static String MY_STORE_SUPPORT = "my.store.suport@gmail.com";

    @Autowired
    private EmailService emailService;


    @Autowired
    private OwnerAccountInfoDao ownerAccountInfoRepository;

    @RequestMapping(value = "profile", method = RequestMethod.GET)
    public String displayProfile(HttpSession session,Model model) {
        StoreInfo store = (StoreInfo) session.getAttribute("store");
        model.addAttribute("store",store);// we have to pass attributename and value
        return "user/profile";
    }

    @RequestMapping(value = "forgotpwd", method = RequestMethod.GET)
    public String getForgotPwd(Model model){

        return "user/forgotPassword";
    }
    @RequestMapping(value = "sendemail", method = RequestMethod.POST)
    public String sendEmail(@RequestParam String email,Model model) throws AddressException, MessagingException, IOException {
        if(email != null) {
            OwnerAccountInfo ownerAccountInfo = ownerAccountInfoRepository.findByEmail(email);
            if(ownerAccountInfo != null){
                sendSimpleMail(ownerAccountInfo);
                return "redirect:/store/login";
            }
        }
        return "redirect:/store/user/forgotpwd";
    }

//    private void sendmail(OwnerAccountInfo ownerAccountInfo) throws AddressException, MessagingException, IOException {
//        Properties props = new Properties();
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.port", "587");
//
//        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication("my.store.suport@gmail.com", "admin@mystore111");
//            }
//        });
//        Message msg = new MimeMessage(session);
//        msg.setFrom(new InternetAddress("my.store.suport@gmail.com", false));
//
//        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(ownerAccountInfo.getEmail()));
//        msg.setSubject("Forgot Password");
//        msg.setContent("Hi "+ownerAccountInfo.getFirstName()+" <br/>Your password is : <b>"
//                +ownerAccountInfo.getPassword()+"</b> <br/> <br/> Thank you for using My Store app<br/> My Store Support", "text/html");
//        msg.setSentDate(new Date(System.currentTimeMillis()));
//
////        MimeBodyPart messageBodyPart = new MimeBodyPart();
////        messageBodyPart.setContent("Tutorials point email", "text/html");
////
////        Multipart multipart = new MimeMultipart();
////        multipart.addBodyPart(messageBodyPart);
////        MimeBodyPart attachPart = new MimeBodyPart();
////
////        attachPart.attachFile("/var/tmp/image19.png");
////        multipart.addBodyPart(attachPart);
////        msg.setContent(multipart);
//        Transport.send(msg);
//    }

    private void sendSimpleMail(OwnerAccountInfo owner) {
        SimpleMailMessage forgotPasswordEmail = new SimpleMailMessage();
        forgotPasswordEmail.setFrom(MY_STORE_SUPPORT);
        forgotPasswordEmail.setTo(owner.getEmail());
        forgotPasswordEmail.setSubject("Forgot Password?");
        forgotPasswordEmail.setSentDate(new Date(System.currentTimeMillis()));
        forgotPasswordEmail.setText("Hi "+owner.getFirstName()+" \n\nYour password is : "
                +owner.getPassword()+" \n\nThank you for using My Store app\n My Store Support");

        emailService.sendEmail(forgotPasswordEmail);
    }

}
