package lk.ijse.green_shadow_crop_system.service.impl;

import lk.ijse.green_shadow_crop_system.dao.UserDao;
import lk.ijse.green_shadow_crop_system.dto.impl.UserDTO;
import lk.ijse.green_shadow_crop_system.entity.impl.UserEntity;
import lk.ijse.green_shadow_crop_system.secure.JWTAuthResponse;
import lk.ijse.green_shadow_crop_system.secure.SignIn;
import lk.ijse.green_shadow_crop_system.service.AuthService;
import lk.ijse.green_shadow_crop_system.service.JWTService;
import lk.ijse.green_shadow_crop_system.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserDao userDao;
    private final Mapping mapping;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public JWTAuthResponse signIn(SignIn signIn) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signIn.getEmail(), signIn.getPassword()));
        var user = userDao.findByEmail(signIn.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var generatedToken = jwtService.generateToken(user);
        return JWTAuthResponse.builder().token(generatedToken).build();
    }

    @Override
    public JWTAuthResponse signUp(UserDTO userDTO) {
        UserEntity savedUser = userDao.save(mapping.toUserEntity(userDTO));
        //Generate the token and return it
        var generatedToken = jwtService.generateToken(savedUser);
        return JWTAuthResponse.builder().token(generatedToken).build();
    }

    @Override
    public JWTAuthResponse refreshToken(String accessToken) {
        //extract username
        var userName = jwtService.extractUserName(accessToken);
        //check the user availability in the DB
        var findUser = userDao.findByEmail(userName)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var refreshToken = jwtService.refreshToken(findUser);
        return JWTAuthResponse.builder().token(refreshToken).build();
    }
}
