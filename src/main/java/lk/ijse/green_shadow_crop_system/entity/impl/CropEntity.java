package lk.ijse.green_shadow_crop_system.entity.impl;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lk.ijse.green_shadow_crop_system.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "crop")
public class CropEntity implements SuperEntity {
}
