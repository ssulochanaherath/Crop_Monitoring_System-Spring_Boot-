package lk.ijse.green_shadow_crop_system.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private String user_id;
    private String email;
    private String password;
    private Role role;
}
