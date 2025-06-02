package com.leon.services;

import com.leon.models.AlertConfiguration;
import com.leon.repositories.AlertConfigurationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AlertConfigurationServiceImpl implements AlertConfigurationService
{
    private static final Logger logger = LoggerFactory.getLogger(AlertConfigurationServiceImpl.class);
    private Map<String, List<AlertConfiguration>> alertConfigurationMap = new HashMap<>();
    @Autowired
    private AlertConfigurationRepository alertConfigurationRepository;

    public void initialize()
    {
        List<AlertConfiguration> result = alertConfigurationRepository.findAll();
        result.forEach(alertConfiguration ->
        {
            if (!alertConfigurationMap.containsKey(alertConfiguration.getOwnerId()))
                alertConfigurationMap.put(alertConfiguration.getOwnerId(), new ArrayList<>());
            List<AlertConfiguration> configurations = alertConfigurationMap.get(alertConfiguration.getOwnerId());
            configurations.add(alertConfiguration);
        });
        logger.info("Loaded alert configuration service with {} alert configuration(s).", result.size());

    }

    @Override
    public void reconfigure() {
        alertConfigurationMap.clear();
        initialize();
    }

    @Override
    public List<AlertConfiguration> getAll() {
        return alertConfigurationMap.values().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    @Override
    public List<AlertConfiguration> getAll(String ownerId)
    {
        if (alertConfigurationMap.containsKey(ownerId)) {
            return alertConfigurationMap.get(ownerId);
        } else {
            logger.warn("No alert configurations found for owner ID: {}", ownerId);
            return Collections.emptyList();
        }
    }

    @Override
    public void deleteAlertConfiguration(String alertConfigurationId)
    {
        Optional<AlertConfiguration> alertConfigurationOptional = alertConfigurationRepository.findById(alertConfigurationId);
        if (alertConfigurationOptional.isPresent())
        {
            AlertConfiguration alertConfiguration = alertConfigurationOptional.get();
            String ownerId = alertConfiguration.getOwnerId();
            alertConfigurationRepository.deleteById(alertConfigurationId);
            List<AlertConfiguration> configurations = alertConfigurationMap.get(ownerId);
            if (configurations != null)
                configurations.removeIf(config -> config.getAlertConfigurationId().equals(alertConfigurationId));

            logger.info("Deleted alert configuration with ID: {}", alertConfigurationId);
        }
        else
            logger.warn("Attempted to delete non-existing alert configuration with ID: {}", alertConfigurationId);
    }

    @Override
    public AlertConfiguration updateAlertConfiguration(AlertConfiguration alertConfiguration)
    {
        if (alertConfiguration == null || alertConfiguration.getAlertConfigurationId() == null || alertConfiguration.getAlertConfigurationId().toString().isEmpty())
        {
            logger.error("Attempted to update an alert configuration with null or empty ID.");
            return null;
        }

        AlertConfiguration updatedAlertConfiguration = alertConfigurationRepository.save(alertConfiguration);
        String ownerId = updatedAlertConfiguration.getOwnerId();

        if (!alertConfigurationMap.containsKey(ownerId))
            alertConfigurationMap.put(ownerId, new ArrayList<>());

        List<AlertConfiguration> configurations = alertConfigurationMap.get(ownerId);
        configurations.removeIf(config -> config.getAlertConfigurationId().equals(updatedAlertConfiguration.getAlertConfigurationId()));
        configurations.add(updatedAlertConfiguration);

        logger.info("Updated alert configuration: {}", updatedAlertConfiguration);
        return updatedAlertConfiguration;
    }

    @Override
    public AlertConfiguration createAlertConfiguration(AlertConfiguration alertConfiguration)
    {
        if (alertConfiguration == null || alertConfiguration.getAlertConfigurationId() == null || alertConfiguration.getAlertConfigurationId().toString().isEmpty())
        {
            logger.error("Attempted to create an alert configuration with null or empty ID.");
            return null;
        }

        if (alertConfiguration.getAlertName() == null || alertConfiguration.getAlertName().isEmpty())
        {
            logger.error("Attempted to create an alert configuration with null or empty name.");
            return null;
        }

        AlertConfiguration createdAlertConfiguration = alertConfigurationRepository.save(alertConfiguration);
        String ownerId = createdAlertConfiguration.getOwnerId();

        if (!alertConfigurationMap.containsKey(ownerId))
            alertConfigurationMap.put(ownerId, new ArrayList<>());

        List<AlertConfiguration> configurations = alertConfigurationMap.get(ownerId);
        configurations.add(createdAlertConfiguration);

        logger.info("Created alert configuration: {}", createdAlertConfiguration);
        return createdAlertConfiguration;
    }
}
