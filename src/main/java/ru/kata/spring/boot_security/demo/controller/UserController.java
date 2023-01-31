package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.security.Principal;

//�������� ��� ��� ����� ����������� ��� ������ � thymeleaf
@Controller
//@RequestMapping �������� ����� URL-����� ��� ������� ������, �������� /lesson/allUsers
@RequestMapping("/lesson")
public class UserController {
    private UserServiceImpl userService;

    //���������� Service ����� �����������. ����� ����� ����, � �� ����� ���� ��� ������
    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    /*
    ����� ��� �������� ������� ��� ����� ���������� ��������� CRUD(Create,Read,Update,Delete)
     - Get, Post, Put, Delete, Patch, � ������.
    @GetMapping � ������������ get-�������.������ �� ������ �� �������, �� ���������, �� ��������,
     ������ �������� ������ � �������
     */
    @GetMapping
    public String mainPage() {
        return "mainPage";
    }

    @GetMapping(value = "/newUser")
    public String authentication(@ModelAttribute("user") User user) { //���������� ������ �����
        return "newUser";
    }

    /*
    @PostMapping-Post-������ �������� ���-�� �� �������, �������� ������ ����� ������� ������,
     ������ ����, ��������� ����
     */
    @PostMapping()
    public String save(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("role", new Role());
        userService.saveUser(user);
        return "redirect:/lesson"; //����� ���������� ������ ���������� �� ������� url
    }
    //Principal ���������� ������ �� �������������� ������������
    @GetMapping("/showUser")
    public String showUser(Principal principal, Model model) {
        User user = userService.findByUserName(principal.getName()).get();
        model.addAttribute("user", user);
        return "showUser";
    }


//    @GetMapping("/registration")
//    public String registration(){
//        return "registration";
//    }
//    @PostMapping("/registration")
//    public String addUser(User user){
//        userService.findByUserName(user.getUserName());
//        user.setRoles();
//        userService.saveUser(user);
//        return "redirect:/login";
//    }


}
