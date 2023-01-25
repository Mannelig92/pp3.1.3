package ru.kata.spring.boot_security.demo.dao;



import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {


    void saveUser(User user); //���������� �����

    void removeUserById(long id); //�������� ������

    List<User> getAllUsers(); //����� ���� ������

    void editUser(User user);

    User getUser(long id);

}
