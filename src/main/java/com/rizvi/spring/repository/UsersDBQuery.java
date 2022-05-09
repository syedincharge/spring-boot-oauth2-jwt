package com.rizvi.spring.repository;


import com.rizvi.spring.entity.UsersDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Repository
public class UsersDBQuery {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public UsersDetail getUserDetails(String username){
        Collection<GrantedAuthority> listOfGrantedAuthorities = new ArrayList<>();
        String userSQLQuery = "SELECT * FROM USERS WHERE USERNAME=?";
        List<UsersDetail> list = jdbcTemplate.query(userSQLQuery, new String [] {username}, (ResultSet rs, int rowNum)
         -> {
              UsersDetail user = new UsersDetail();
              user.setUsername(username);

              user.setPassword(rs.getString("PASSWORD"));
              return user;
         });
        if (list.size() > 0 ){
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_SYSTEMADMIN");
            listOfGrantedAuthorities.add(grantedAuthority);
            list.get(0).setListOfGrantedAuthorities(listOfGrantedAuthorities);
            return list.get(0);
        }
        return null;
    }
}
