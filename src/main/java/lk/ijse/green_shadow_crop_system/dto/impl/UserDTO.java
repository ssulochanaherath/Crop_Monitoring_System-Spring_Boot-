package lk.ijse.green_shadow_crop_system.dto.impl;

import lk.ijse.green_shadow.dto.UserStatus;
import lk.ijse.green_shadow.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO implements UserStatus {
    private String email;
    private String password;
    private Role role;
}
