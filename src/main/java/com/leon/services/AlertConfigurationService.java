package com.leon.services;

import com.leon.models.AlertConfiguration;
import java.util.List;

public interface AlertConfigurationService {
    void reconfigure();

    List<AlertConfiguration> getAll();

    List<AlertConfiguration> getAll(String ownerId);

    void deleteAlertConfiguration(String alertConfigurationId);

    AlertConfiguration updateAlertConfiguration(AlertConfiguration alertConfiguration);

    AlertConfiguration createAlertConfiguration(AlertConfiguration alertConfiguration);
}
