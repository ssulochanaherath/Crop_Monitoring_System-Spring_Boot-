package lk.ijse.green_shadow_crop_system.service;

import lk.ijse.green_shadow_crop_system.dto.VehicleStatus;
import lk.ijse.green_shadow_crop_system.dto.impl.VehicleDTO;
import lk.ijse.green_shadow_crop_system.entity.impl.VehicleEntity;

import java.util.List;
import java.util.Optional;

public interface VehicleService {
    void saveVehicle(VehicleDTO vehicleDTO);

    List<VehicleDTO> getAllVehicles();

    VehicleStatus getVehicle(String vehicleCode);

    void deleteVehicle(String vehicleCode);

    void updateVehicle(String vehicleCode,VehicleDTO vehicleDTO);

    Optional<VehicleEntity> findByLicenseNumber(String licenseNumber);
}
