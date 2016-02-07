package com.springapp.mvc;

import org.junit.*;
import static org.junit.Assert.assertEquals;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Nikita on 05.02.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/test-configuration.xml")
public class MYSQLDAOTest {
    @Autowired
    RestDAO serviceDAO;

    @Test
    public void testGetUserById() {
        int id = 1;
        String name = "nikita";
        String password = "12345";
        String phone = "123-12-13";
        User expectedUser = new User(id, name, password, phone, AuthorityUtils.NO_AUTHORITIES);
        User actualUser = serviceDAO.getUserByID(1);
        assertEquals(expectedUser.getId(), actualUser.getId());
        assertEquals(expectedUser.getName(), actualUser.getName());
        assertEquals(expectedUser.getPassword(), actualUser.getPassword());
        assertEquals(expectedUser.getPhone(), actualUser.getPhone());
    }
}
