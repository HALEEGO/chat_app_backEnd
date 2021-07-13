package com.example.hackathonex.model;


import lombok.Data;

@Data
public class User {
    public enum Role {
        HOST, PARTICIPANT
    }
    private Role role;
    private String userName;

}
