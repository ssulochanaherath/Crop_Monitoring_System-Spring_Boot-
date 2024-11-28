package lk.ijse.green_shadow_crop_system.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.green_shadow_crop_system.customStatusCodes.SelectedErrorStatus;
import lk.ijse.green_shadow_crop_system.dao.FieldDao;
import lk.ijse.green_shadow_crop_system.dao.StaffDao;
import lk.ijse.green_shadow_crop_system.dto.FieldStatus;
import lk.ijse.green_shadow_crop_system.dto.impl.FieldDTO;
import lk.ijse.green_shadow_crop_system.dto.impl.StaffDTO;
import lk.ijse.green_shadow_crop_system.entity.impl.CropEntity;
import lk.ijse.green_shadow_crop_system.entity.impl.FieldEntity;
import lk.ijse.green_shadow_crop_system.entity.impl.StaffEntity;
import lk.ijse.green_shadow_crop_system.exception.FieldNotFoundException;
import lk.ijse.green_shadow_crop_system.exception.StaffNotFoundException;
import lk.ijse.green_shadow_crop_system.service.FieldService;
import lk.ijse.green_shadow_crop_system.util.AppUtil;
import lk.ijse.green_shadow_crop_system.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class FieldServiceImpl implements FieldService{
    @Autowired
    private FieldDao fieldDao;
    @Autowired
    private StaffDao staffDao;
    @Autowired
    private Mapping mapping;
    @Override
    public void saveField(FieldDTO fieldDTO) {
        fieldDTO.setField_code(AppUtil.generateFieldId());
        FieldEntity fieldEntity = fieldDao.save(mapping.toFieldEntity(fieldDTO));
        if (fieldEntity == null) {
            throw new FieldNotFoundException("Field not found");
        }
    }

    @Override
    public List<FieldDTO> getAllFields() {
        return mapping.toFieldDTOList(fieldDao.findAll());
    }

    @Override
    public FieldStatus getField(String fieldCode) {
        if(fieldDao.existsById(fieldCode)){
            var selectedField = fieldDao.getReferenceById(fieldCode);
            return mapping.toFieldDTO(selectedField);
        }else {
            return new SelectedErrorStatus(2,"Selected field not found");
        }
    }

    @Override
    public void deleteField(String fieldCode) {
        Optional<FieldEntity> foundField = fieldDao.findById(fieldCode);
        if(foundField.isPresent()){
            throw new FieldNotFoundException("Field not found");
        }else {
            fieldDao.deleteById(fieldCode);
        }
    }

    @Override
    public void updateField(String fieldCode, FieldDTO fieldDTO) {
        Optional<FieldEntity> tmpField = fieldDao.findById(fieldCode);
        if(!tmpField.isPresent()){
            throw new FieldNotFoundException("Field not found");
        }else{
            tmpField.get().setField_name(fieldDTO.getField_name());
            tmpField.get().setLocation(fieldDTO.getLocation());
            tmpField.get().setExtent_size(fieldDTO.getExtent_size());
            tmpField.get().setField_image1(fieldDTO.getField_image1());
            tmpField.get().setField_image2(fieldDTO.getField_image2());
            List<CropEntity> cropEntityList = mapping.toCropEntityList(fieldDTO.getCrops());
            tmpField.get().setCrops(cropEntityList);
            List<StaffEntity> staffEntityList = mapping.toStaffEntityList(fieldDTO.getAllocated_staff());
            tmpField.get().setAllocated_staff(staffEntityList);
        }
    }

    @Override
    public void updateAllocatedStaff(String fieldCode,List<String> staffId) {
        Optional<FieldEntity> tmpField = fieldDao.findById(fieldCode);
        if(!tmpField.isPresent()){
            throw new FieldNotFoundException("Field not found");
        }
        FieldEntity fieldEntity = tmpField.get();
        for(String staff : staffId){
            Optional<StaffEntity> tmpStaff = staffDao.findById(staff);
            if(!tmpStaff.isPresent()){
                throw new StaffNotFoundException("Staff not found");
            }else{
                StaffEntity staffEntity = tmpStaff.get();
                fieldEntity.getAllocated_staff().add(staffEntity);
                staffEntity.getFields().add(fieldEntity);
                staffDao.save(staffEntity);
            }
        }
        fieldDao.save(fieldEntity);
    }

    @Override
    public List<String> getAllFieldNames() {
        List<FieldEntity> fieldEntities = fieldDao.findAll();
        return fieldEntities.stream()
                .map(FieldEntity::getField_name)
                .collect(Collectors.toList());
    }

    @Override
    public FieldDTO getFieldByName(String field_name) {
        Optional<FieldEntity> tmpField = fieldDao.findByFieldName(field_name);
        if(!tmpField.isPresent()){
            throw new FieldNotFoundException("Field not found");
        }
        return mapping.toFieldDTO(tmpField.get());
    }

    @Override
    public List<FieldDTO> getFieldListByName(List<String> field_name) {
        if(field_name == null || field_name.isEmpty()){
            return Collections.emptyList();
        }

        List<FieldEntity> fieldEntities = fieldDao.findByFieldNameList(field_name);

        if(fieldEntities.isEmpty()){
            throw new FieldNotFoundException("Field not found");
        }

        return fieldEntities.stream()
                .map(mapping::toFieldDTO)
                .collect(Collectors.toList());
    }
}
