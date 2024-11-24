package lk.ijse.green_shadow_crop_system.entity.impl;

import jakarta.persistence.*;
import lk.ijse.green_shadow_crop_system.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Field")
public class FieldEntity implements SuperEntity {
    @Id
    private String field_code;
    private String field_name;
    private Point location;
    private Double extent_size;
    @Column(columnDefinition = "LONGTEXT")
    private String field_image1;
    @Column(columnDefinition = "LONGTEXT")
    private String field_image2;
    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL)
    private List<CropEntity> crops = new ArrayList<>();
    @ManyToMany(mappedBy = "fields")
    private List<StaffEntity> allocated_staff = new ArrayList<>();
}
