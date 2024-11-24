package lk.ijse.green_shadow_crop_system.dao;

import lk.ijse.green_shadow_crop_system.entity.impl.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<UserEntity,String> {
}
