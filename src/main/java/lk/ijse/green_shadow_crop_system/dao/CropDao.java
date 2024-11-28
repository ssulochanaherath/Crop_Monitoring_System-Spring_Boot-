package lk.ijse.green_shadow_crop_system.dao;

import lk.ijse.green_shadow_crop_system.entity.impl.CropEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public class CropDao implements JpaRepository<CropEntity, String> {
    @Query("SELECT c FROM CropEntity c WHERE c.common_name IN :crops")
    List<CropEntity> findByCropNameList(@Param("crops") List<String> crops);
}
