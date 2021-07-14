package com.example.hackathonex.DTO;
import com.example.hackathonex.constant.Enum.Role;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserDTO {
    private Role role;
    private String userName;
}
