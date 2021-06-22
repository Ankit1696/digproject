package com.example.digisole.controller;

import com.example.digisole.model.Users;
import com.example.digisole.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/add")
    public ResponseEntity<Users> save (@RequestBody Users users) throws Exception {
        userService.save(users);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/fetch")
    public ResponseEntity<List<Users>> get(){
        List<Users> userList = userService.getUsers();
        return new ResponseEntity(userList, HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<Users> edit(@RequestBody Users users) throws Exception {
        userService.edit(users, users.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/search/{data}")
    public ResponseEntity<List<Users>> search(@PathVariable String data){
        List<Users> usersList =  userService.Search(data);

        return new ResponseEntity<>(usersList, HttpStatus.OK);

    }

    @DeleteMapping("/delete/{data}")
    public ResponseEntity delete(@PathVariable String data) throws Exception {
        userService.delete(data);
        return new ResponseEntity("data deleted successfully", HttpStatus.OK);
    }


}
