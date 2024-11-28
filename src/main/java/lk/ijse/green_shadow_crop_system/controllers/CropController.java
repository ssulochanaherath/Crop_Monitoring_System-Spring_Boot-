package lk.ijse.green_shadow_crop_system.controllers;

import lk.ijse.green_shadow_crop_system.dto.impl.CropDTO;
import lk.ijse.green_shadow_crop_system.dto.impl.FieldDTO;
import lk.ijse.green_shadow_crop_system.service.CropService;
import lk.ijse.green_shadow_crop_system.service.FieldService;
import lk.ijse.green_shadow_crop_system.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/crop")
@CrossOrigin
public class CropController {
    @Autowired
    private CropService cropService;
    @Autowired
    private FieldService fieldService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveCrop(@RequestParam("common_name") String commonName,
                                         @RequestParam ("scientific_name") String scientificName,
                                         @RequestPart ("crop_image") MultipartFile cropImage,
                                         @RequestParam ("category") String category,
                                         @RequestParam ("season") String season,
                                         @RequestParam ("field_name") String field_name
    ){
        String base64CropImage = "";

        try {
            FieldDTO field = fieldService.getFieldByName(field_name);
            byte[] bytesCropImage = cropImage.getBytes();
            base64CropImage = AppUtil.cropImageToBase64(bytesCropImage);

            String crop_code = AppUtil.generateCropId();

            CropDTO buildCropDTO = new CropDTO();
            buildCropDTO.setCrop_code(crop_code);
            buildCropDTO.setCommon_name(commonName);
            buildCropDTO.setScientific_name(scientificName);
            buildCropDTO.setCrop_image(base64CropImage);
            buildCropDTO.setCategory(category);
            buildCropDTO.setSeason(season);
            buildCropDTO.setField(field);
            cropService.saveCrop(buildCropDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
