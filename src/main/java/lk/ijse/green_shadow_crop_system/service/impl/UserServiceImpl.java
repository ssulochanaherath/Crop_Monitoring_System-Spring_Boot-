package lk.ijse.green_shadow_crop_system.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.green_shadow_crop_system.dao.UserDao;
import lk.ijse.green_shadow_crop_system.dto.impl.UserDTO;
import lk.ijse.green_shadow_crop_system.entity.impl.UserEntity;
import lk.ijse.green_shadow_crop_system.exception.DataPersistException;
import lk.ijse.green_shadow_crop_system.exception.UserNotFoundException;
import lk.ijse.green_shadow_crop_system.service.UserService;
import lk.ijse.green_shadow_crop_system.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveUser(UserDTO userDTO) {
        UserEntity saveUser = userDao.save(mapping.toUserEntity(userDTO));
        if(saveUser == null) {
            throw new DataPersistException("User not saved");
        }
    }

    @Override
    public UserDetailsService userDetailsService() {
        return userName ->
                userDao.findByEmail(userName)
                        .orElseThrow(()-> new UserNotFoundException("User Not Found"));
    }
}
