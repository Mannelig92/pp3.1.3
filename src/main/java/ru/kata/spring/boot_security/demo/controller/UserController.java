package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

//�������� ��� ��� ����� ����������� ��� ������ � thymeleaf
@Controller
//@RequestMapping �������� ����� URL-����� ��� ������� ������, �������� /lesson/allUsers
@RequestMapping("/lesson")
public class UserController {
    private UserService userService;

    //���������� Service ����� �����������. ����� ����� ����, � �� ����� ���� ��� ������
    @Autowired
    public UserController(UserService userService) {
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

    /*
    Mapping ��������� ������ ����������� � ��� ������� � ������� �� ����� ���������� �� ��������
    ����� 5 ����� Mapping � ����������� �� ���� ����� http-������ ������ ������ � ���� ����� �����������
    Model ��� ������ ������ � �������.
     */
    @GetMapping(value = "/allUsers") //����������� �������� url
    public String showAllUsers(Model model) { //����� ���� ������
        //����� � model ���� ����,��������. � Thymeleaf ���������� �������� �� �����
        model.addAttribute("allUsers", userService.getAllUsers()); //allUsers ����� ��������� � Thymeleaf
        return "allUsers"; //��������� � html ����� �� ������ �� ��������
    }

    @GetMapping(value = "/newUser")
    public String saveNewUser(@ModelAttribute("user") User user) { //���������� ������ �����
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

    //��������� ����� �� id. ������ id ����� ����� ��������� ����� � � ������� ��������� PathVariable
    //�� ������� ���� id �� url � ������� � ���� ������
    //PathVariable ����������� ���� id
    @GetMapping(value = "/{id}/edit")
    public String editUser(@PathVariable("id") long id, Model model) {
        //�������� �������� �������� �� ��� id
        model.addAttribute("user", userService.getUser(id));
        return "edit";
    }

    @PatchMapping(value = "/{id}")
    public String update(@ModelAttribute("user") User user) {
        userService.editUser(user);
        return "redirect:/lesson/allUsers";
    }

    @DeleteMapping(value = "/{id}/delete")
    public String delete(@PathVariable("id") long id) {
        userService.removeUserById(id);
        return "redirect:/lesson/allUsers";
    }
}
