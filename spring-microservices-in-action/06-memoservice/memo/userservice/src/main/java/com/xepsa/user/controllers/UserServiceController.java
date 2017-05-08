package com.xepsa.user.controllers;

import com.xepsa.user.model.User;
import com.xepsa.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.UUID;

@RestController
@RequestMapping(value="v1/user")
public class UserServiceController {
    
    @Autowired
    private UserService userService;

    @RequestMapping(value="/{userId}", method = RequestMethod.GET)
    public User getUser(@PathVariable("userId") String userId) {

        // return userService.getUser(userId);

        final String id = UUID.randomUUID().toString();
        final String title = "Mr";
        final String givenName = "Temple";
        final String familyName = "Cloud";

        User user = new User().
                withId(id).
                withTitle(title).
                withGivenName(givenName).
                withFamilyName(familyName);

        return user;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public String createUser(@RequestBody User user) {
        userService.saveUser(user);
        return String.format("This is the post");
    }

    @RequestMapping(value="{userId}",method = RequestMethod.PUT)
    public String updateUser(@PathVariable("userId") String userId,
                             @RequestBody User user) {
        return String.format("This is the put");
    }


    @RequestMapping(value="{userId}",method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteUser(@PathVariable("userId") String userId) {
        return String.format("This is the Delete");
    }
}
