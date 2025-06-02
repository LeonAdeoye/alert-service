package com.leon.repositories;

import com.leon.models.AlertTypeDetail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface AlertTypeRepository extends MongoRepository<AlertTypeDetail, UUID> {}