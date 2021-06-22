package com.example.digisole.validators;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class Validation {
    public boolean validateName(String name){
        String regex = "^[a-zA-Z\\s]*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    public boolean validatePhone(String phoneNumber){
        if(phoneNumber.length()==11 && phoneNumber.charAt(0)=='0'){
            phoneNumber = phoneNumber.substring(1);
        }
        //+91 0 1 2
        if(phoneNumber.length()==13 && phoneNumber.substring(0,3 ).equals("+91")){
            phoneNumber = phoneNumber.substring(3);
        }

        String regex = ("^\\d{10}$");
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);

        return matcher.matches();


    }

    public boolean validatePinCode(String pinCode){


        String regex = ("^\\d{6}$");
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(pinCode);

        return matcher.matches();


    }

    public static void main(String[] args) {
        System.out.println(new Validation().validateName("Ankit Kumar"));
        System.out.println(new Validation().validatePhone("Ankit Kumar1"));
        System.out.println(new Validation().validatePhone("7061522719"));
        System.out.println(new Validation().validatePhone("07061522719"));
        System.out.println(new Validation().validatePhone("+917061522719"));
        System.out.println(new Validation().validatePhone("706152271a"));
        System.out.println(new Validation().validatePinCode("7061"));
        System.out.println(new Validation().validatePinCode("70615"));
        System.out.println(new Validation().validatePinCode("706155"));
        System.out.println(new Validation().validatePinCode("7061551"));
        System.out.println(new Validation().validatePinCode("7061551aa"));
    }
}
