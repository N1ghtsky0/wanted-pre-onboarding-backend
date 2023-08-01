package com.example.demo.api.user.repository;

import com.example.demo.api.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {

    Boolean existsBySignInId(String signInId);

}
