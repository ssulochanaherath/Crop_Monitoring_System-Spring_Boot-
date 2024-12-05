package lk.ijse.green_shadow_crop_system.service;

import lk.ijse.green_shadow_crop_system.dto.impl.UserDTO;
import lk.ijse.green_shadow_crop_system.secure.JWTAuthResponse;
import lk.ijse.green_shadow_crop_system.secure.SignIn;

public interface AuthService {
    JWTAuthResponse signIn(SignIn signIn);
    JWTAuthResponse signUp(UserDTO userDTO);
    JWTAuthResponse refreshToken(String accessToken);
}
