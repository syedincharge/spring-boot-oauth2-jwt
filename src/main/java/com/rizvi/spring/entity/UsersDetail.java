package com.rizvi.spring.entity;


//import com.sun.xml.txw2.annotation.XmlElement;
import org.springframework.security.core.GrantedAuthority;

//import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collection;
//@XmlRootElement
public class UsersDetail {

    private String username;
    private String password;
    private Collection<GrantedAuthority> listOfGrantedAuthorities = new ArrayList<>();

    public UsersDetail() {
    }

    public UsersDetail(String username, String password, Collection<GrantedAuthority> listOfGrantedAuthorities) {
        this.username = username;
        this.password = password;
        this.listOfGrantedAuthorities = listOfGrantedAuthorities;
    }

    public String getUsername() {
        return username;
    }
    //@XmlElement
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
  //  @XmlElement
    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<GrantedAuthority> getListOfGrantedAuthorities() {
        return listOfGrantedAuthorities;
    }
  //  @XmlElement
    public void setListOfGrantedAuthorities(Collection<GrantedAuthority> listOfGrantedAuthorities) {
        this.listOfGrantedAuthorities = listOfGrantedAuthorities;
    }

    @Override
    public String toString() {
        return "UsersDetail{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", listOfGrantedAuthorities=" + listOfGrantedAuthorities +
                '}';
    }
}
