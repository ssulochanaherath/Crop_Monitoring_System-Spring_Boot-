package lk.ijse.green_shadow_crop_system.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.green_shadow_crop_system.customStatusCodes.SelectedErrorStatus;
import lk.ijse.green_shadow_crop_system.dao.CropDao;
import lk.ijse.green_shadow_crop_system.dto.CropStatus;
import lk.ijse.green_shadow_crop_system.dto.impl.CropDTO;
import lk.ijse.green_shadow_crop_system.entity.impl.CropEntity;
import lk.ijse.green_shadow_crop_system.entity.impl.FieldEntity;
import lk.ijse.green_shadow_crop_system.exception.CropNotFoundException;
import lk.ijse.green_shadow_crop_system.exception.DataPersistException;
import lk.ijse.green_shadow_crop_system.service.CropService;
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
public class CropServiceImpl implements CropService {
    @Autowired
    private CropDao cropDao;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveCrop(CropDTO cropDTO) {
        cropDTO.setCrop_code(AppUtil.generateCropId());
        CropEntity saveCrop = cropDao.save(mapping.toCropEntity(cropDTO));
        if(saveCrop == null) {
            throw new DataPersistException("Crop not saved");
        }
    }

    @Override
    public List<CropDTO> getAllCrops() {
       return mapping.toCropDTOList(cropDao.findAll());
    }

    @Override
    public CropStatus getCrop(String cropCode) {
        if(cropDao.existsById(cropCode)) {
            var selectedCrop = cropDao.getReferenceById(cropCode);
            return mapping.toCropDTO(selectedCrop);
        }else {
            return new SelectedErrorStatus(2,"Selected crop does not exist");
        }
    }

    @Override
    public void deleteCrop(String cropCode) {
        Optional<CropEntity> foundCrop = cropDao.findById(cropCode);
        if(foundCrop.isPresent()) {
            throw new CropNotFoundException("Crop not found");
        }else {
            cropDao.deleteById(cropCode);
        }
    }

    @Override
    public void updateCrop(String cropCode, CropDTO cropDTO) {
        Optional<CropEntity> tmpCrop = cropDao.findById(cropCode);
        if(!tmpCrop.isPresent()) {
            throw new CropNotFoundException("Crop not found");
        }else {
            tmpCrop.get().setCommon_name(cropDTO.getCommon_name());
            tmpCrop.get().setScientific_name(cropDTO.getScientific_name());
            tmpCrop.get().setCrop_image(cropDTO.getCrop_image());
            tmpCrop.get().setCategory(cropDTO.getCategory());
            tmpCrop.get().setSeason(cropDTO.getSeason());
            FieldEntity fieldEntity = mapping.toFieldEntity(cropDTO.getField());
            tmpCrop.get().setField(fieldEntity);
        }
    }

    @Override
    public List<String> getAllCropNames() {
        List<CropEntity> cropEntities = cropDao.findAll();
        return cropEntities.stream()
                .map(CropEntity::getCommon_name)
                .collect(Collectors.toList());
    }

    @Override
    public List<CropDTO> getCropListByName(List<String> crops) {
        if(crops.isEmpty() || crops == null) {
            return Collections.emptyList();
        }
        List<CropEntity> cropEntities = cropDao.findByCropNameList(crops);

        if(cropEntities.isEmpty()) {
            throw new CropNotFoundException("Crop not found");
        }
        return cropEntities.stream()
                .map(mapping::toCropDTO)
                .collect(Collectors.toList());
    }
}
