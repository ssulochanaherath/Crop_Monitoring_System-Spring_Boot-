package lk.ijse.green_shadow_crop_system.service;

import lk.ijse.green_shadow.dto.FieldStatus;
import lk.ijse.green_shadow.dto.impl.FieldDTO;
import lk.ijse.green_shadow.entity.impl.FieldEntity;

import java.util.List;
import java.util.Optional;

public interface FieldService {
    void saveField(FieldDTO fieldDTO);

    List<FieldDTO> getAllFields();

    FieldStatus getField(String fieldCode);

    void deleteField(String fieldCode);

    void updateField(String fieldName,FieldDTO fieldDTO);

    void updateAllocatedStaff(String fieldCode, List<String> staffId);

    List<String> getAllFieldNames();

    FieldDTO getFieldByName(String field_name);

    List<FieldDTO> getFieldListByName(List<String> field_name);

    Optional<FieldEntity> findByFieldName(String fieldName);
}
