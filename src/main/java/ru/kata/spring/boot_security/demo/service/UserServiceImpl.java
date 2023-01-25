package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

/*
���� ����� ������������ ����� ������ � ��������� ������-����.
������ �������� �������� ������ @Component.
������������� ������ ��������� �������� ������ ����-������� �������������.
 */
@Service //������ �������� �������������� ������ ����� ������������ � ���
public class UserServiceImpl implements UserService { //����� ������� ��� ������ � �����
    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    @Transactional
    public void removeUserById(long id) {
        userDao.removeUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    @Transactional
    public void editUser(User user) {
        userDao.editUser(user);
    }


    @Override
    public User getUser(long id) {
        return userDao.getUser(id);
    }
}
