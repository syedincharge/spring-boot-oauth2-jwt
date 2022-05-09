package com.rizvi.spring.utility;

import com.rizvi.spring.entity.UsersDetail;
import org.springframework.security.core.userdetails.User;



public class UsersHelper extends User {

    private static final long serialVersionUID = 1L;

    public UsersHelper(UsersDetail user){

          super(user.getUsername(),
                user.getPassword(),
                user.getListOfGrantedAuthorities() );

    }


}
