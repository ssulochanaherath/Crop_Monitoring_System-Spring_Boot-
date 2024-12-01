package lk.ijse.green_shadow_crop_system.dto.impl;

import lk.ijse.green_shadow_crop_system.dto.StaffStatus;
import lk.ijse.green_shadow_crop_system.entity.Designation;
import lk.ijse.green_shadow_crop_system.entity.Gender;
import lk.ijse.green_shadow_crop_system.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StaffDTO implements StaffStatus {
    private String id;
    private String first_name;
    private String last_name;
    private Designation designation;
    private Gender gender;
    private String joined_date;
    private String dob;
    private String address;
    private String contact_no;
    private String email;
    private Role role;
    private List<FieldDTO> fields;

}