package com.springapp.mvc;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;

/**
 * Created by Никита on 16.11.2015.
 */
public interface RestDAO {
    User getUserByID(int id);
    User getUserByName(String name);
    List<User> getAllUsers();
    User getUserByNameWithAuthorities(String name);
    List<GrantedAuthority> getGrantedAuthorities(String name);
    Post getPostByID(int id);
    List<Post> getAllPosts();
    List<Post> getAllUserPosts(int id);
}
