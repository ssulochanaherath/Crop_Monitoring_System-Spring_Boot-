package lk.ijse.green_shadow_crop_system.entity.impl;

import jakarta.persistence.*;
import lk.ijse.green_shadow_crop_system.entity.Designation;
import lk.ijse.green_shadow_crop_system.entity.Gender;
import lk.ijse.green_shadow_crop_system.entity.Role;
import lk.ijse.green_shadow_crop_system.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "staff")
public class StaffEntity implements SuperEntity {
    @Id
    private String id;
    private String first_name;
    private String last_name;
    @Enumerated(EnumType.STRING)
    private Designation designation;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String joined_date;
    private String dob;
    private String address;
    private String contact_no;
    @Column(unique = true)
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;
}
