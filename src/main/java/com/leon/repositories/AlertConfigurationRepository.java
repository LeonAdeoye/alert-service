package com.leon.repositories;

import com.leon.models.AlertConfiguration;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface AlertConfigurationRepository extends MongoRepository<AlertConfiguration, UUID> {}