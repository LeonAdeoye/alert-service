package com.leon.controllers;

import com.leon.models.AlertConfiguration;
import com.leon.services.AlertConfigurationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController()
@RequestMapping("/alert/configuration")
public class AlertConfigurationController
{
    private static final Logger logger = LoggerFactory.getLogger(AlertConfigurationController.class);

    @Autowired
    private AlertConfigurationService alertConfigurationService;

    @CrossOrigin
    @RequestMapping(value="/reconfigure", method=GET)
    public ResponseEntity<Void> reconfigure()
    {
        logger.info("Received request to reconfigure.");
        this.alertConfigurationService.reconfigure();
        return ResponseEntity.noContent().build();
    }

    @CrossOrigin
    @RequestMapping(method=GET, produces = "application/json")
    public ResponseEntity<List<AlertConfiguration>> getAll()
    {
        logger.info("Received request to get all alert configurations.");
        return new ResponseEntity<>(this.alertConfigurationService.getAll(), HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(method=POST , consumes = "application/json"  ,produces = "application/json")
    public ResponseEntity<AlertConfiguration> createAlertConfiguration(@RequestBody AlertConfiguration alertConfiguration)
    {
        if (alertConfiguration == null || alertConfiguration.getId() == null || alertConfiguration.getId().toString().isEmpty()) {
            logger.error("Attempted to create an alert configuration with null or empty ID.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(alertConfiguration.getAlertName() == null || alertConfiguration.getAlertName().isEmpty()) {
            logger.error("Attempted to create an alert configuration with null or empty name.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        logger.info("Received request to create alert configuration: {}", alertConfiguration);
        AlertConfiguration createdAlertConfiguration = alertConfigurationService.createAlertConfiguration(alertConfiguration);
        return new ResponseEntity<>(createdAlertConfiguration, HttpStatus.CREATED);
    }


    @CrossOrigin
    @RequestMapping(value = "/{alertConfigurationId}", method = DELETE)
    public ResponseEntity<Void> deleteAlertConfiguration(@PathVariable String alertConfigurationId)
    {
        logger.info("Received request to delete alert configuration with ID: {}", alertConfigurationId);
        alertConfigurationService.deleteAlertConfiguration(alertConfigurationId);
        return ResponseEntity.noContent().build();
    }

    @CrossOrigin
    @RequestMapping(method = PUT, consumes = "application/json"  ,produces = "application/json")
    public ResponseEntity<AlertConfiguration> updateAlertConfiguration(@RequestBody AlertConfiguration alertConfiguration)
    {
        if (alertConfiguration == null || alertConfiguration.getId() == null || alertConfiguration.getId().toString().isEmpty())
        {
            logger.error("Attempted to update an alert configuration with null or empty ID.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        logger.info("Received request to update alert configuration: {}", alertConfiguration);
        AlertConfiguration updatedAlertConfiguration = alertConfigurationService.updateAlertConfiguration(alertConfiguration);
        return new ResponseEntity<>(updatedAlertConfiguration, HttpStatus.OK);
    }
}
