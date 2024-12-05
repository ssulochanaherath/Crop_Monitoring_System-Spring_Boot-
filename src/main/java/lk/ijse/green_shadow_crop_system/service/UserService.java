package lk.ijse.green_shadow_crop_system.service;

import lk.ijse.green_shadow.dto.impl.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    void saveUser(UserDTO userDTO);
    UserDetailsService userDetailsService();
}
