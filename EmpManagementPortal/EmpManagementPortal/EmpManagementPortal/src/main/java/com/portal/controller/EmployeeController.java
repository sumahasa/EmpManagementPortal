package com.portal.controller;

import com.portal.modal.User;
import com.portal.repo.UserRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private UserRepository userRepo;


    @PostMapping("/emp/getprofile")
    public ResponseEntity<Object> getprofile(@RequestBody User obj)
    {
        JSONObject jsonObject=new JSONObject();
        System.out.println("obj"+obj);
        User userObj=userRepo.getUserByMail(obj.getEmail());
        System.out.println("user"+userObj);
        if(userObj !=null)
        {
            return new ResponseEntity<>(userObj,HttpStatus.OK);
        }
        else {
            jsonObject.put("status","Something went wrong !");
            return new ResponseEntity<>(jsonObject,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
