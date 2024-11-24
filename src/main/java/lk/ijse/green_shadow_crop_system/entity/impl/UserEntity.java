package lk.ijse.green_shadow_crop_system.entity.impl;

import jakarta.persistence.*;
import lk.ijse.green_shadow_crop_system.entity.Role;
import lk.ijse.green_shadow_crop_system.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "user")
public class UserEntity implements SuperEntity {
    @Id
    private String user_id;
    @Column(unique = true)
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
}
