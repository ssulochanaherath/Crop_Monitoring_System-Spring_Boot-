package lk.ijse.green_shadow_crop_system.service;

import lk.ijse.green_shadow_crop_system.dto.FieldStatus;
import lk.ijse.green_shadow_crop_system.dto.impl.FieldDTO;

import java.util.List;

public interface FieldService {
    void saveField(FieldDTO fieldDTO);

    List<FieldDTO> getAllFields();

    FieldStatus getField(String fieldCode);

    void deleteField(String fieldCode);

    void updateField(String fieldCode,FieldDTO fieldDTO);

    void updateAllocatedStaff(String fieldCode, List<String> staffId);

    List<String> getAllFieldNames();

    FieldDTO getFieldByName(String field_name);

    List<FieldDTO> getFieldListByName(List<String> field_name);
}
