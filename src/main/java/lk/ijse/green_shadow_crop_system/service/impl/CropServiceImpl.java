package lk.ijse.green_shadow_crop_system.service.impl;

import lk.ijse.green_shadow_crop_system.dao.CropDao;
import lk.ijse.green_shadow_crop_system.service.CropService;
import lk.ijse.green_shadow_crop_system.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

public class CropServiceImpl implements CropService {
    @Autowired
    private CropDao cropDao;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveCrop(CropDTO cropDTO) {

    }
}
