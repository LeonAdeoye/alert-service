package com.leon.controllers;

import com.leon.models.AlertTypeDetail;
import com.leon.services.AlertTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController()
@RequestMapping("/alert/type")
public class AlertTypeController
{
    private static final Logger logger = LoggerFactory.getLogger(AlertTypeController.class);

    @Autowired
    private AlertTypeService alertTypeService;

    @CrossOrigin
    @RequestMapping(value="/reconfigure", method=GET)
    public ResponseEntity<Void> reconfigure()
    {
        logger.info("Received request to reconfigure.");
        this.alertTypeService.reconfigure();
        return ResponseEntity.noContent().build();
    }

    @CrossOrigin
    @RequestMapping(method=GET, produces = "application/json")
    public ResponseEntity<List<AlertTypeDetail>> getAll()
    {
        logger.info("Received request to get all alert types.");
        return new ResponseEntity<>(this.alertTypeService.getAll(), HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(method=POST , consumes = "application/json"  ,produces = "application/json")
    public ResponseEntity<AlertTypeDetail> createAlertType(@RequestBody AlertTypeDetail alertTypeDetail)
    {
        if (alertTypeDetail == null || alertTypeDetail.getId() == null || alertTypeDetail.getId().toString().isEmpty()) {
            logger.error("Attempted to create an alert type with null or empty ID.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(alertTypeDetail.getAlertType() == null || alertTypeDetail.getAlertType().getAlertType().isEmpty())
        {
            logger.error("Attempted to create an alert type with null or empty name.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        logger.info("Received request to create alert type: {}", alertTypeDetail);
        AlertTypeDetail createdAlertType = alertTypeService.createAlertType(alertTypeDetail);
        return new ResponseEntity<>(createdAlertType, HttpStatus.CREATED);
    }


    @CrossOrigin
    @RequestMapping(value = "/{alertTypeId}", method = DELETE)
    public ResponseEntity<Void> deleteAlertType(@PathVariable String alertTypeId)
    {
        logger.info("Received request to delete alert type with ID: {}", alertTypeId);
        alertTypeService.deleteAlertType(alertTypeId);
        return ResponseEntity.noContent().build();
    }

    @CrossOrigin
    @RequestMapping(method = PUT, consumes = "application/json"  ,produces = "application/json")
    public ResponseEntity<AlertTypeDetail> updateAlertType(@RequestBody AlertTypeDetail alertTypeDetail)
    {
        if (alertTypeDetail == null || alertTypeDetail.getId() == null || alertTypeDetail.getId().toString().isEmpty())
        {
            logger.error("Attempted to update an alert type with null or empty ID.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        logger.info("Received request to update alert type: {}", alertTypeDetail);
        AlertTypeDetail updatedAlertType = alertTypeService.updateAlertType(alertTypeDetail);
        return new ResponseEntity<>(updatedAlertType, HttpStatus.OK);
    }
}