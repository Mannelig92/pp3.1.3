package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

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
    public String save(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/lesson/allUsers"; //����� ���������� ������ ���������� �� ������� url
    }

    @GetMapping(value = "/{id}/showUser")
    public String showUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
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
