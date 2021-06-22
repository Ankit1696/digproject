package com.example.digisole.repository;

import com.example.digisole.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {

    Optional<Users> findByZipCode(String zipCode);

    Optional<Users> findByName(String data);

    Object deleteByName(String data);
}
