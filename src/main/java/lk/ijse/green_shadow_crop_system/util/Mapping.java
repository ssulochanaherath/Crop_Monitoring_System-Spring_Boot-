package lk.ijse.green_shadow_crop_system.util;

import lk.ijse.green_shadow_crop_system.dto.impl.UserDTO;
import lk.ijse.green_shadow_crop_system.entity.impl.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;
    public UserEntity toUserEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, UserEntity.class);
    }
}
