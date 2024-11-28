package lk.ijse.green_shadow_crop_system.service;

import lk.ijse.green_shadow_crop_system.dto.CropStatus;
import lk.ijse.green_shadow_crop_system.dto.impl.CropDTO;

import java.util.List;

public interface CropService {
    void saveCrop(CropDTO cropDTO);

    List<CropDTO> getAllCrops();

    CropStatus getCrop(String cropCode);

    void deleteCrop(String cropCode);

    void updateCrop(String cropCode,CropDTO cropDTO);

    List<String> getAllCropNames();

    List<CropDTO> getCropListByName(List<String> crops);
}
