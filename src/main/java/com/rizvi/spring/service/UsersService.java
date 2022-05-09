package com.rizvi.spring.service;


import com.rizvi.spring.entity.UsersDetail;
import com.rizvi.spring.repository.UsersDBQuery;
import com.rizvi.spring.utility.UsersHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UsersService implements UserDetailsService {

    @Autowired
    private UsersDBQuery usersDBQuery;

    @Override
    public UsersHelper loadUserByUsername(final String username) throws UsernameNotFoundException {
        UsersDetail users_Detail = null;
        try{
            users_Detail = usersDBQuery.getUserDetails(username);
            UsersHelper userDetail = new UsersHelper(users_Detail);
            return userDetail;
        }catch (Exception e){
            e.printStackTrace();
            throw new UsernameNotFoundException("User  "+ username +"  was not found in the database");
        }
    }
}
