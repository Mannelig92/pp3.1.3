package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/*
@Repository ������������ ��� �������� ����, ��� ����� �������������
�������� ��� ��������, ����������, ������, ���������� � �������� �������� ��� ��������� .
@Component � ��� ��������� ������ ������ . �� ������������ ��� ����������� ������ ��� ����������.
�� ����� ������������ @Component � ����������, ����� �������� bean-���������� ��� ����������� ���������� Spring.
 */
@Repository
@Component
public class UserDaoImpl implements UserDao { //Dao ��� ���������� � ��
    // ��������� @PersistenceContext �������������� ��� ��������������� ���������� ��������� ��������� � �����.
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    //���� ������� ���������� @Transactional , �� ����� ���� ����������� ��� ������, ���� ������������ ���� �������� ���� �� ����
    @Transactional
    public void saveUser(User user) {
        entityManager.persist(user); //����� persist ��������� entity ������� �� �������
    }

    @Override
    @Transactional
    public void removeUserById(long id) {
        entityManager.remove(entityManager.find(User.class, id)); //������� �� �� �� id
    }

    @Override
    public List<User> getAllUsers() {
        //���� ������� JPQL. u ��� users
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    @Transactional
    public void editUser(User user) {
        entityManager.merge(user);
    }


    @Override
    public User getUser(long id) {
        return entityManager.find(User.class, id);
    }
}
