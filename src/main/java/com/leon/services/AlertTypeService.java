package com.leon.services;

import com.leon.models.AlertTypeDetail;
import java.util.List;

public interface AlertTypeService {
    AlertTypeDetail createAlertType(AlertTypeDetail alertTypeDetail);
    void deleteAlertType(String id);
    AlertTypeDetail updateAlertType(AlertTypeDetail alertTypeDetail);
    List<AlertTypeDetail> getAll();
    void reconfigure();
}
