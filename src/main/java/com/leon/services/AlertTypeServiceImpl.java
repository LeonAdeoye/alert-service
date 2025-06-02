package com.leon.services;

import com.leon.models.AlertType;
import com.leon.models.AlertTypeDetail;
import com.leon.repositories.AlertTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class AlertTypeServiceImpl implements AlertTypeService
{
    private static final Logger logger = LoggerFactory.getLogger(AlertTypeServiceImpl.class);
    private Map<AlertType, List<AlertTypeDetail>> alertTypeDetailMap = new HashMap<>();
    @Autowired
    private AlertTypeRepository alertTypeRepository;

    @PostConstruct
    public void initialize()
    {
        List<AlertTypeDetail> result = alertTypeRepository.findAll();
        result.forEach(alertTypeDetail ->
        {
            if (!alertTypeDetailMap.containsKey(alertTypeDetail.getAlertType()))
                alertTypeDetailMap.put(alertTypeDetail.getAlertType(), new ArrayList<>());
            List<AlertTypeDetail> details = alertTypeDetailMap.get(alertTypeDetail.getAlertType());
            details.add(alertTypeDetail);
        });
        logger.info("Loaded alert type service with {} alert type(s).", result.size());
    }

    @Override
    public AlertTypeDetail createAlertType(AlertTypeDetail alertTypeDetail)
    {
        if (alertTypeDetail == null || alertTypeDetail.getId() == null || alertTypeDetail.getId().toString().isEmpty())
        {
            logger.error("Attempted to create an alert type with null or empty ID.");
            return null;
        }

        if (alertTypeDetail.getAlertType() == null || alertTypeDetail.getAlertType().getAlertType().isEmpty())
        {
            logger.error("Attempted to create an alert type with null or empty name.");
            return null;
        }

        alertTypeRepository.save(alertTypeDetail);
        alertTypeDetailMap.computeIfAbsent(alertTypeDetail.getAlertType(), k -> new ArrayList<>()).add(alertTypeDetail);
        logger.info("Created alert type: {}", alertTypeDetail.getAlertType());
        return alertTypeDetail;
    }

    @Override
    public void deleteAlertType(String id)
    {
        Optional<AlertTypeDetail> optionalDetail = alertTypeRepository.findById(UUID.fromString(id));
        if (optionalDetail.isPresent())
        {
            AlertTypeDetail alertTypeDetail = optionalDetail.get();
            alertTypeRepository.delete(alertTypeDetail);
            List<AlertTypeDetail> details = alertTypeDetailMap.get(alertTypeDetail.getAlertType());
            if (details != null)
            {
                details.remove(alertTypeDetail);
                logger.info("Deleted alert type: {}", alertTypeDetail.getAlertType());
            }
        }
        else
            logger.warn("No alert type found with ID: {}", id);
    }

    @Override
    public AlertTypeDetail updateAlertType(AlertTypeDetail alertTypeDetail)
    {
        AlertTypeDetail existingDetail = alertTypeRepository.findById(alertTypeDetail.getId()).orElse(null);
        if (existingDetail != null)
        {
            alertTypeRepository.save(alertTypeDetail);
            List<AlertTypeDetail> details = alertTypeDetailMap.get(alertTypeDetail.getAlertType());
            if (details != null)
            {
                details.remove(existingDetail);
                details.add(alertTypeDetail);
                logger.info("Updated alert type: {}", alertTypeDetail.getAlertType());
            }
            return alertTypeDetail;
        }
        else
        {
            logger.warn("No alert type found with ID: {}", alertTypeDetail.getId());
            return null;
        }
    }

    @Override
    public List<AlertTypeDetail> getAll()
    {
        List<AlertTypeDetail> allDetails = new ArrayList<>();
        for (List<AlertTypeDetail> details : alertTypeDetailMap.values()) {
            allDetails.addAll(details);
        }
        logger.info("Retrieved {} alert type details.", allDetails.size());
        return allDetails;
    }

    @Override
    public void reconfigure()
    {
        alertTypeDetailMap.clear();
        initialize();
    }
}
