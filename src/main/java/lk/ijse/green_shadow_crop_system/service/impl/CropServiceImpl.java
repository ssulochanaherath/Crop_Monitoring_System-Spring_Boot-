package lk.ijse.green_shadow_crop_system.service.impl;

import lk.ijse.green_shadow_crop_system.dao.CropDao;
import lk.ijse.green_shadow_crop_system.dto.impl.CropDTO;
import lk.ijse.green_shadow_crop_system.entity.impl.CropEntity;
import lk.ijse.green_shadow_crop_system.service.CropService;
import lk.ijse.green_shadow_crop_system.util.AppUtil;
import lk.ijse.green_shadow_crop_system.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

public class CropServiceImpl implements CropService {
    @Autowired
    private CropDao cropDao;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveCrop(CropDTO cropDTO) {
        cropDTO.setCrop_code(AppUtil.generateCropId());
        CropEntity saveCrop = cropDao.save(mapping.toCropEntity(cropDTO));
        if (saveCrop == null) {
            throw new RuntimeException("Failed to save crop");
        }
    }
}
