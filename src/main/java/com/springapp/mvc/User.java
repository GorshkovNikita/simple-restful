package com.springapp.mvc;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Класс, расширяющий возможности интерфейса {@link org.springframework.security.core.userdetails.UserDetails}
 * Он добавляет новые поля к объекту класса {@link org.springframework.security.core.userdetails.User},
 * которые есть в базе данных.
 * Также можно напрямую реализовать интерфейс {@link org.springframework.security.core.userdetails.UserDetails},
 * скорее всего это лучше, но оставлю на потом.
 *
 * Created by Никита on 16.11.2015.
 */
public class User extends org.springframework.security.core.userdetails.User {
    private int id;
    private String name;
    private String password;
    private String phone;

    public User(int id, String username, String password, String phone,
                Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.id = id;
        this.name = username;
        this.password = password;
        this.phone = phone;
    }

    public User(int id, String username, String password, String phone, boolean enabled,
                    boolean accountNonExpired, boolean credentialsNonExpired,
                    boolean accountNonLocked,
                    Collection authorities) {

        super(username, password, enabled, accountNonExpired,
                credentialsNonExpired, accountNonLocked, authorities);

        this.id = id;
        this.name = username;
        this.password = password;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
