package com.leon.repositories;

import com.leon.models.AlertConfiguration;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertConfigurationRepository extends MongoRepository<AlertConfiguration, String> {}