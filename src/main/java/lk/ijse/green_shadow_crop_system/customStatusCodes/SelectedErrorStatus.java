package lk.ijse.green_shadow_crop_system.customStatusCodes;

import lk.ijse.green_shadow_crop_system.dto.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectedErrorStatus implements CropStatus, FieldStatus, EquipmentStatus,
        MonitoringLogStatus, VehicleStatus, StaffStatus {
    private int statusCode;
    private String statusMessage;
}
