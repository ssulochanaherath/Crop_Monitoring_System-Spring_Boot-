package lk.ijse.green_shadow_crop_system.dto.impl;

import lk.ijse.green_shadow_crop_system.dto.EquipmentStatus;
import lk.ijse.green_shadow_crop_system.entity.EquipmentType;
import lk.ijse.green_shadow_crop_system.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EquipmentDTO implements EquipmentStatus {
    private String equipment_id;
    private String name;
    private EquipmentType type;
    private Status status;
    private StaffDTO assigned_staff;
    private FieldDTO assigned_field;
}
