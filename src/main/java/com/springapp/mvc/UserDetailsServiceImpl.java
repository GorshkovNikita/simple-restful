package com.springapp.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Обычно используется реализация {@link org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl}
 * Этот класс служит новой реализацией {@link org.springframework.security.core.userdetails.UserDetailsService }
 * для добавления собственных полей к интерфейсу {@link org.springframework.security.core.userdetails.UserDetails}
 *
 * Created by Никита on 12.01.2016.
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private RestDAO serviceDAO;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        return serviceDAO.getUserByNameWithAuthorities(name);
    }
}
