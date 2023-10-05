package sst.userservice.controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import sst.userservice.model.User;
import sst.userservice.service.UserService;


import java.util.List;

@RestController()
public class UserController   {





    @Autowired
    UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUser(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/users")
    public ResponseEntity<User> createNewUser(@Validated @RequestBody User user){
        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping("/users/{id}")
        public ResponseEntity<User> getUser(@PathVariable Long id){
        return  ResponseEntity.ok(userService.getUser(id));
    }


    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@Validated @RequestBody User user, @PathVariable Long id) {
        return ResponseEntity.ok(userService.updateUser(user,id));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Boolean> deletedUser(@PathVariable Long id){
        return ResponseEntity.ok(userService.deleteUser(id));
    }

}
