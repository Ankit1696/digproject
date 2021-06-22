package com.example.digisole.service;

import com.example.digisole.model.Users;
import com.example.digisole.repository.UserRepo;
import com.example.digisole.validators.Validation;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private Validation validation;

    public void save(Users users) throws Exception {
       String errorMessage="";
        if(validation.validateName(users.getName())==false){
            errorMessage += "please enter a valid user_name\n";
        }
        if(validation.validatePhone(users.getPhoneNo())==false){
            errorMessage += "please enter a valid phone no\n";
        }
        if(validation.validatePinCode(users.getZipCode())==false){
            errorMessage += "please enter a valid pin/zip code\n";
        }

        if(validation.validatePinCode(users.getZipCode()) && validation.validatePhone(users.getPhoneNo())&&
        validation.validateName(users.getName())){

            if(userRepo.findByZipCode(users.getZipCode()).isPresent()){
                throw new Exception("zip code should be unique");
            }

            userRepo.save(users);
        }
        else {
            throw new Exception(errorMessage);
        }

    }

    public List<Users> getUsers() {
        return userRepo.findAll();
    }

    public void edit(Users users, int id) throws Exception {
        String errorMessage="";
        if(validation.validateName(users.getName())==false){
            errorMessage += "please enter a valid user_name\n";
        }
        if(validation.validatePhone(users.getPhoneNo())==false){
            errorMessage += "please enter a valid phone no\n";
        }
        if(validation.validatePinCode(users.getZipCode())==false){
            errorMessage += "please enter a valid pin/zip code\n";
        }



        if(validation.validatePinCode(users.getZipCode()) && validation.validatePhone(users.getPhoneNo())&&
                validation.validateName(users.getName())){

            userRepo.save(users);
        }
        else {
            throw new Exception(errorMessage);
        }

    }

    public List<Users> Search(String data) {
        List<Users> usersList = userRepo.findAll();
        List<Users> res = new ArrayList<>();

        for(int i=0;i<usersList.size();i++){
            if((usersList.get(i).getName().equalsIgnoreCase(data) )
                    || usersList.get(i).getPhoneNo().toString().equalsIgnoreCase(data)
                    ||(usersList.get(i).getCity()).equalsIgnoreCase(data)
                    ||(usersList.get(i).getZipCode()).toString().equalsIgnoreCase(data)){
                Users user = usersList.get(i);
                res.add(user);
            }
        }

        return res;
    }

    public void delete(String data) throws Exception {
        if(userRepo.findByName(data).isPresent()){
            userRepo.deleteByName(data);
        }
        else throw new Exception("Please enter a valid name");
    }
}
