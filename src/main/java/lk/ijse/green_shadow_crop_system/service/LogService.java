package lk.ijse.green_shadow_crop_system.service;

import lk.ijse.green_shadow_crop_system.dto.MonitoringLogStatus;
import lk.ijse.green_shadow_crop_system.dto.impl.MonitoringLogDTO;
import lk.ijse.green_shadow_crop_system.entity.impl.MonitoringLogEntity;

import java.util.List;
import java.util.Optional;

public interface LogService {
    void saveLog(MonitoringLogDTO monitoringLogDTO);

    List<MonitoringLogDTO> getAllLogs();

    MonitoringLogStatus getLog(String logCode);

    void deleteLog(String logCode);

    void updateLog(String logCode,MonitoringLogDTO monitoringLogDTO);

    Optional<MonitoringLogEntity> findByLogDesc(String logDesc);
}
