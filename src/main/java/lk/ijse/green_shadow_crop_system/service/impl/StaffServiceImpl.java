package lk.ijse.green_shadow_crop_system.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.green_shadow_crop_system.dao.FieldDao;
import lk.ijse.green_shadow_crop_system.dao.StaffDao;
import lk.ijse.green_shadow_crop_system.dto.StaffStatus;
import lk.ijse.green_shadow_crop_system.dto.impl.FieldDTO;
import lk.ijse.green_shadow_crop_system.dto.impl.StaffDTO;
import lk.ijse.green_shadow_crop_system.entity.impl.FieldEntity;
import lk.ijse.green_shadow_crop_system.entity.impl.StaffEntity;
import lk.ijse.green_shadow_crop_system.exception.StaffNotFoundException;
import lk.ijse.green_shadow_crop_system.service.StaffService;
import lk.ijse.green_shadow_crop_system.util.AppUtil;
import lk.ijse.green_shadow_crop_system.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffDao staffDao;
    @Autowired
    private FieldDao fieldDao;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveStaff(StaffDTO staffDTO) {
        staffDTO.setId(AppUtil.generateStaffId());
        StaffEntity staffEntity = staffDao.save(mapping.toStaffEntity(staffDTO));
        if(staffEntity == null) {
            throw new StaffNotFoundException("Staff Member Not Found");
        }
    }

    @Override
    public List<StaffDTO> getAllStaff() {
        List<StaffEntity> staffs = staffDao.findAll();
        return staffs.stream()
                .map(staff -> {
                    StaffDTO staffDTO = new StaffDTO();
                    staffDTO.setFirst_name(staff.getFirst_name());
                    staffDTO.setLast_name(staff.getLast_name());
                    staffDTO.setDesignation(staff.getDesignation());
                    staffDTO.setGender(staff.getGender());
                    staffDTO.setJoined_date(staff.getJoined_date());
                    staffDTO.setDob(staff.getDob());
                    staffDTO.setAddress(staff.getAddress());
                    staffDTO.setContact_no(staff.getContact_no());
                    staffDTO.setEmail(staff.getEmail());
                    staffDTO.setRole(staff.getRole());
                    List<FieldDTO> assignedFieldDTO = new ArrayList<>();
                    for (FieldEntity field : staff.getFields()) {
                        Optional<FieldEntity> fieldOpt = fieldDao.findById(field.getField_name());
                        if (fieldOpt.isPresent()) {
                            assignedFieldDTO.add(mapping.toFieldDTO(fieldOpt.get()));
                        }
                    }
                    staffDTO.setFields(assignedFieldDTO);
                    return staffDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public StaffStatus getStaff(String id) {
        if(staffDao.existsById(id)){
            var selectedStaff = staffDao.getReferenceById(id);
            return mapping.toStaffDTO(selectedStaff);
        }else {
            return new SelectedErrorStatus(2,"Selected Staff Member Not Found");
        }
    }

    @Override
    public void deleteStaff(String id) {
        Optional<StaffEntity> foundStaff = staffDao.findById(id);
        if(foundStaff.isPresent()) {
            throw new StaffNotFoundException("Staff Member Not Found");
        }else {
            staffDao.deleteById(id);
        }
    }

    @Override
    public void updateStaff(String firstName, StaffDTO staffDTO) {
        Optional<StaffEntity> tmpStaff = staffDao.findByStaffName(firstName);
        if(!tmpStaff.isPresent()) {
            throw new StaffNotFoundException("Staff Member Not Found");
        }else{
            tmpStaff.get().setFirst_name(staffDTO.getFirst_name());
            tmpStaff.get().setLast_name(staffDTO.getLast_name());
            tmpStaff.get().setDesignation(staffDTO.getDesignation());
            tmpStaff.get().setGender(staffDTO.getGender());
            tmpStaff.get().setJoined_date(staffDTO.getJoined_date());
            tmpStaff.get().setDob(staffDTO.getDob());
            tmpStaff.get().setAddress(staffDTO.getAddress());
            tmpStaff.get().setContact_no(staffDTO.getContact_no());
            tmpStaff.get().setEmail(staffDTO.getEmail());
            tmpStaff.get().setRole(staffDTO.getRole());
            List<FieldEntity> fieldEntityList = mapping.toFieldEntityList(staffDTO.getFields());
            tmpStaff.get().setFields(fieldEntityList);
        }
    }

    @Override
    public List<String> getAllStaffNames() {
        List<StaffEntity> staffEntities = staffDao.findAll();
        return staffEntities.stream()
                .map(StaffEntity::getFirst_name)
                .collect(Collectors.toList());
    }

    @Override
    public List<StaffDTO> getStaffListByName(List<String> staffs) {
        if(staffs.isEmpty() || staffs == null){
            return Collections.emptyList();
        }

        List<StaffEntity> staffEntities = staffDao.findByStaffNameList(staffs);

        if(staffEntities.isEmpty()){
            throw new StaffNotFoundException("Staff Member Not Found");
        }

        return staffEntities.stream()
                .map(mapping::toStaffDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StaffDTO getStaffByName(String assignedStaff) {
        Optional<StaffEntity> tmpStaff = staffDao.findByStaffName(assignedStaff);
        if(!tmpStaff.isPresent()){
            throw new StaffNotFoundException("Staff Member Not Found");
        }
        return mapping.toStaffDTO(tmpStaff.get());
    }
}