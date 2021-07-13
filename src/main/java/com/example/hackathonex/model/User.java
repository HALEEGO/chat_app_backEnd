package com.example.hackathonex.model;


import lombok.Getter;

@Getter
public class User {
    public enum Role {
        HOST, PARTICIPANT
    }
    private Role role;
    private String Name;

}
