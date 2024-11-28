package lk.ijse.green_shadow_crop_system.service.impl;

import lk.ijse.green_shadow_crop_system.dao.CropDao;
import lk.ijse.green_shadow_crop_system.service.CropService;
import org.springframework.beans.factory.annotation.Autowired;

public class CropServiceImpl implements CropService {
    @Autowired
    private CropDao cropDao;
}
