package lk.ijse.green_shadow_crop_system.dto.impl;

import lk.ijse.green_shadow_crop_system.dto.VehicleStatus;
import lk.ijse.green_shadow_crop_system.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehicleDTO implements VehicleStatus {
    private String vehicle_code;
    private String licensePlateNumber;
    private String vehicleCategory;
    private String fuelType;
    private Status status;
    private String remarks;
    private StaffDTO assigned_staff;
}
