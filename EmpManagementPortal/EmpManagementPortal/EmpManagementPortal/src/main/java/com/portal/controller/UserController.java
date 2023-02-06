package com.portal.controller;

import com.portal.modal.User;
import com.portal.repo.UserRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody User obj)
    {
        JSONObject jsonObject=new JSONObject();
        System.out.println("obj"+obj);
        User u=userRepo.getUserByMail(obj.getEmail());
        System.out.println("user"+u);
        if(u == null)
        {
            jsonObject.put("status","User Not Registered");
            jsonObject.put("role","NA");
            return new ResponseEntity<Object>(jsonObject.toString(), HttpStatus.UNAUTHORIZED);
        }
        else if(u.getRole().equals(1) && obj.getPassword().equals(u.getPassword()))
        {
            jsonObject.put("status","Successfully validated");
            jsonObject.put("role","admin");
            return new ResponseEntity<Object>(jsonObject.toString(), HttpStatus.OK);
        }
        else if(u.getRole().equals(0) && obj.getPassword().equals(u.getPassword())){
            jsonObject.put("status","Successfully validated");
            jsonObject.put("role","user");
            return new ResponseEntity<Object>(jsonObject.toString(), HttpStatus.OK);
        }
        else {
            jsonObject.put("status","Wrong details !");
            jsonObject.put("role","NA");
            return new ResponseEntity<Object>(jsonObject.toString(), HttpStatus.UNAUTHORIZED);
        }
    }
}
