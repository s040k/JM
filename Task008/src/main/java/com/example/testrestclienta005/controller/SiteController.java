package com.example.testrestclienta005.controller;

import com.example.testrestclienta005.model.Role;
import com.example.testrestclienta005.model.User;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Properties;

@Controller
@RequestMapping("/")
public class SiteController {

    private final String url = "http://localhost:8080/rest/users/";

    @GetMapping("/")
    public String indexPage() {
        return "ajaxPage";
    }

    @GetMapping(value = "/allusers", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public User[] allUsersPage(){
        String url = "http://localhost:8080/rest/users/";
        RestTemplate restTemplate = new RestTemplate();
        User[] list = restTemplate.getForObject(url, User[].class);

        return list;
    }

    @GetMapping(value = "/newUser", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public User newUser(){
        User user = new User();
        user.setRole(new Role());
        return user;
    }

    @GetMapping(value = "/deleteUser")
    @ResponseBody
    public String deleteUser(HttpServletRequest request) {

        String id = request.getParameter("id");
        String urlDelete = url + id;

        RestTemplate restTemplate = new RestTemplate();

        restTemplate.delete(urlDelete);
        return "redirect:/allusers";
    }


    @PostMapping(value = "/addUser", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String addUser(@RequestParam("json") String jsonUser) {

        Gson gson = new Gson();
        User user = gson.fromJson(jsonUser, User.class);

        HttpEntity<User> entity = new HttpEntity<>(user);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity(url, entity, User.class);

        return "redirect:/allusers";
    }

    @PostMapping(value = "/updateUser", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String updateUser(@RequestParam("json") String jsonUser) {

        Gson gson = new Gson();
        User user = gson.fromJson(jsonUser, User.class);

        HttpEntity<User> entity = new HttpEntity<>(user);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(url, entity, User.class);

        return "redirect:/allusers";
    }

}
