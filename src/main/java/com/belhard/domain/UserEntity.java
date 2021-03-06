package com.belhard.domain;

import com.belhard.util.Role;
import java.util.List;

public class UserEntity extends AbstractEntity {

    private static final long serialVersionUID = 6297385302078200511L;
    private String firstName;
    private String email;
    private Integer id;
    private String lastName;
    private String password;
    private List<Role> roles;

    public UserEntity() {
        super();
    }

    public void setName(String firstName) {
        this.firstName = firstName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List roles) {
        this.roles = roles;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "UserEntity{" + "firstName=" + firstName + ", email=" + email + 
                ", id=" + id + ", lastName=" + lastName + ", password=" + password + 
                ", roles=" + roles + '}';
    }

}
