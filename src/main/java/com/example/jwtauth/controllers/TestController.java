package com.example.jwtauth.controllers;

import com.example.jwtauth.models.User;
import com.example.jwtauth.pojo.InfoResponse;
import com.example.jwtauth.pojo.MessageResponse;
import com.example.jwtauth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/test")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TestController {
    @Autowired
    UserRepository userRepository;

    // Если пользователь не получает доступа к методу,
    // то ему будет выдан соответсвующий ответ в каждом из методов
    // ответ на этот запрос получит кто угодно, без токена
    // @return сообщение
    @GetMapping("/all")
    public String getAll() {
        return "public API: welcome to our RESTaurant";
    }

     // Доступ получит только пользователь,
     // передавший токен, подтверждающий, что он
     // имеет роль администратора/шефа/покупателя получит в ответ
     // информацию о пользователе с ником, который был передан
     // @param username ник пользователя, о котором надо вывести информацию
     // @return информация о пользователе с ником username
    @GetMapping("/info")
    @PreAuthorize("hasRole('ADMIN') or hasRole('CHEF') or hasRole('CUSTOMER')")
    public ResponseEntity<?> getInfoApi(@RequestBody String username) {
        if (!userRepository.existsByUsername(username)) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: User with this username does not exist"));
        } else {
            User user = userRepository.findByUsername(username).get();
            InfoResponse response = new InfoResponse(user.getUsername(), user.getEmail(), user.getRoles());
            return ResponseEntity.ok(response);
        }
    }

     // Доступ имеют только user'ы с ролью CUSTOMER
     // @return сообщение
    @GetMapping("/customer")
    @PreAuthorize("hasRole('CUSTOMER')")
    public String getUserApi() {
        return "customer Api: imagine that you are actually able to buy something";
    }

     // Доступ имеют только user'ы с ролью CHEF
     // @return сообщение
    @GetMapping("/chef")
    @PreAuthorize("hasRole('CHEF')")
    public String GetChefApi() {
        return "chef api: let's pretend you cooked something";
    }

     // Доступ имеют только user'ы с ролью ADMIN
     // @return сообщение
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String GetChefAdmin() {
        return "admin api: yes, you are admin. Or manager. Or whatever";
    }
}
