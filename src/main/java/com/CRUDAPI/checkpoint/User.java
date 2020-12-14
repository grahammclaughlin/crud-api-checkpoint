package com.CRUDAPI.checkpoint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @JsonView(UserViews.Common.class)
    private Long id;
    @JsonView(UserViews.Common.class)
    private String email;

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void update(User u){
        if(u.getId() instanceof Number){
            setId(u.getId());
        }
        if(u.getEmail() instanceof String){
            setEmail(u.getEmail());
        }
        if(u.getPassword() instanceof String){
            setPassword(u.getPassword());
        }
    }
    public boolean equals(User u){
        return u.getEmail().equals(this.getEmail()) &&
                u.getPassword().equals(this.getPassword());
    }
}
