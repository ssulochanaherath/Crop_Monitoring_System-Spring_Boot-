package lk.ijse.green_shadow_crop_system.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.green_shadow_crop_system.dao.FieldDao;
import lk.ijse.green_shadow_crop_system.service.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class FieldServiceImpl implements FieldService {
    @Autowired
    private FieldDao fieldDao;
}