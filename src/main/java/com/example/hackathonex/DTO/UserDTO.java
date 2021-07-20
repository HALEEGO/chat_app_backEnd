package com.example.hackathonex.DTO;
import com.example.hackathonex.constant.Enum.Role;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@NoArgsConstructor
public class UserDTO {
    private Role role;
    private String userName;
}
