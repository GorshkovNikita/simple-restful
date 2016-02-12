package com.springapp.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;

/**
 * Created by Никита on 16.11.2015.
 */
@Service
public class MySQLDAO implements RestDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User getUserByID(int id) {
        return this.jdbcTemplate.queryForObject(
                "select * from users where id = ?",
                new Object[] { id },
                new RowMapper<User>() {
                    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                        int id = rs.getInt("id");
                        String name = rs.getString("name");
                        String password = rs.getString("password");
                        String phone = rs.getString("phone");
                        return new User(id, name, password, phone, AuthorityUtils.NO_AUTHORITIES);
                    }
                });
    }

    @Override
    public User getUserByName(String name) {
        return this.jdbcTemplate.queryForObject(
                "select * from users where name = ?",
                new Object[] { name },
                new RowMapper<User>() {
                    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                        int id = rs.getInt("id");
                        String name = rs.getString("name");
                        String password = rs.getString("password");
                        String phone = rs.getString("phone");
                        return new User(id, name, password, phone, AuthorityUtils.NO_AUTHORITIES);
                    }
                });
    }

    @Override
    public List<User> getAllUsers() {
        return this.jdbcTemplate.query(
                "select * from users",
                new RowMapper<User>() {
                    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                        int id = rs.getInt("id");
                        String name = rs.getString("name");
                        String password = rs.getString("password");
                        String phone = rs.getString("phone");
                        return new User(id, name, password, phone, AuthorityUtils.NO_AUTHORITIES);
                    }
                });
    }

    @Override
    public User getUserByNameWithAuthorities(String name) {
        return this.jdbcTemplate.queryForObject(
                "select * from users where name = ?",
                new Object[] { name },
                new RowMapper<User>() {
                    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                        int id = rs.getInt("id");
                        String name = rs.getString("name");
                        String password = rs.getString("password");
                        String phone = rs.getString("phone");
                        List<GrantedAuthority> authorities = getGrantedAuthorities(name);
                        return new User(id, name, password, phone, authorities);
                    }
                });
    }

    @Override
    public List<GrantedAuthority> getGrantedAuthorities(String name) {
        return this.jdbcTemplate.query("select u.name, au.authority from users u, authorities au where u.id = au.user_id and u.name = ?",
                new String[] { name }, new RowMapper<GrantedAuthority>() {
                    public GrantedAuthority mapRow(ResultSet rs, int rowNum)
                            throws SQLException {
                        String roleName = rs.getString(2);
                        return new SimpleGrantedAuthority(roleName);
                    }
                });
    }

    @Override
    public Post getPostByID(int id) {
        return this.jdbcTemplate.queryForObject(
                "select * from posts where id = ?",
                new Object[] { id },
                new RowMapper<Post>() {
                    public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Post post = new Post();
                        post.setId(rs.getInt("id"));
                        post.setAuthor(getUserByID(rs.getInt("author_id")));
                        post.setContent(rs.getString("content"));
                        post.setDate(rs.getTimestamp("date"));
                        return post;
                    }
                });
    }

    @Override
    public List<Post> getAllPosts() {
        return this.jdbcTemplate.query(
                "select * from posts",
                new RowMapper<Post>() {
                    public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Post post = new Post();
                        post.setId(rs.getInt("id"));
                        post.setAuthor(getUserByID(rs.getInt("author_id")));
                        post.setContent(rs.getString("content"));
                        post.setDate(rs.getTimestamp("date"));
                        return post;
                    }
                });
    }

    @Override
    public List<Post> getAllUserPosts(int id) {
        return this.jdbcTemplate.query(
                "select * from posts where author_id = ?",
                new Object[] { id },
                new RowMapper<Post>() {
                    public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Post post = new Post();
                        post.setId(rs.getInt("id"));
                        post.setAuthor(getUserByID(rs.getInt("author_id")));
                        post.setContent(rs.getString("content"));
                        post.setDate(rs.getTimestamp("date"));
                        return post;
                    }
                }
        );
    }
}
