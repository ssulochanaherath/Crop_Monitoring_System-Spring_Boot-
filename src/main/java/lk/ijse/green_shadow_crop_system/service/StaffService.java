package lk.ijse.green_shadow_crop_system.service;

import lk.ijse.green_shadow_crop_system.dto.StaffStatus;
import lk.ijse.green_shadow_crop_system.dto.impl.StaffDTO;

import java.util.List;

public interface StaffService {
    void saveStaff(StaffDTO staffDTO);

    List<StaffDTO> getAllStaff();

    StaffStatus getStaff(String id);

    void deleteStaff(String id);

    void updateStaff(String id,StaffDTO staffDTO);
    List<String> getAllStaffNames();

    List<StaffDTO> getStaffListByName(List<String> staffs);
}
