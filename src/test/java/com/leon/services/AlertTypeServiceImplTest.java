package com.leon.services;
import com.leon.models.AlertType;
import com.leon.models.AlertTypeDetail;
import com.leon.repositories.AlertTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AlertTypeServiceImplTest {

    @Mock
    private AlertTypeRepository alertTypeRepository;

    @InjectMocks
    private AlertTypeServiceImpl alertTypeService;

    private AlertTypeDetail alertTypeDetail;

    @BeforeEach
    void setUp() {
        //openMocks(this);

        alertTypeDetail = new AlertTypeDetail();
        alertTypeDetail.setId(UUID.randomUUID());
        alertTypeDetail.setAlertType(AlertType.ALGO_EXPIRED_WITH_EXECUTIONS_OUTSTANDING);
        alertTypeDetail.setExplanation("Test Explanation");
    }

    @Test
    void testInitialize() {
        when(alertTypeRepository.findAll()).thenReturn(Collections.singletonList(alertTypeDetail));

        alertTypeService.initialize();

        assertTrue(alertTypeService.getAll().contains(alertTypeDetail));
        verify(alertTypeRepository, times(1)).findAll();
    }

    @Test
    void testCreateAlertTypeValid() {
        when(alertTypeRepository.save(alertTypeDetail)).thenReturn(alertTypeDetail);

        AlertTypeDetail createdDetail = alertTypeService.createAlertType(alertTypeDetail);

        assertNotNull(createdDetail);
        assertEquals(alertTypeDetail, createdDetail);
        verify(alertTypeRepository, times(1)).save(alertTypeDetail);
    }

    @Test
    void testCreateAlertTypeInvalid() {
        AlertTypeDetail invalidDetail = new AlertTypeDetail();

        AlertTypeDetail result = alertTypeService.createAlertType(invalidDetail);

        assertNull(result);
        verify(alertTypeRepository, never()).save(any(AlertTypeDetail.class));
    }

    @Test
    void testDeleteAlertTypeValid() {
        when(alertTypeRepository.findById(alertTypeDetail.getId())).thenReturn(Optional.of(alertTypeDetail));

        alertTypeService.deleteAlertType(alertTypeDetail.getId().toString());

        verify(alertTypeRepository, times(1)).delete(alertTypeDetail);
    }

    @Test
    void testDeleteAlertTypeInvalid() {
        when(alertTypeRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        alertTypeService.deleteAlertType(UUID.randomUUID().toString());

        verify(alertTypeRepository, never()).delete(any(AlertTypeDetail.class));
    }

    @Test
    void testUpdateAlertTypeValid() {
        when(alertTypeRepository.findById(alertTypeDetail.getId())).thenReturn(Optional.of(alertTypeDetail));
        when(alertTypeRepository.save(alertTypeDetail)).thenReturn(alertTypeDetail);

        AlertTypeDetail updatedDetail = alertTypeService.updateAlertType(alertTypeDetail);

        assertNotNull(updatedDetail);
        assertEquals(alertTypeDetail, updatedDetail);
        verify(alertTypeRepository, times(1)).save(alertTypeDetail);
    }

    @Test
    void testUpdateAlertTypeInvalid() {
        when(alertTypeRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        AlertTypeDetail result = alertTypeService.updateAlertType(alertTypeDetail);

        assertNull(result);
        verify(alertTypeRepository, never()).save(any(AlertTypeDetail.class));
    }

    @Test
    void testGetAll() {
        when(alertTypeRepository.findAll()).thenReturn(Collections.singletonList(alertTypeDetail));

        alertTypeService.initialize();
        List<AlertTypeDetail> allDetails = alertTypeService.getAll();

        assertEquals(1, allDetails.size());
        assertTrue(allDetails.contains(alertTypeDetail));
    }

    @Test
    void testReconfigure() {
        when(alertTypeRepository.findAll()).thenReturn(Collections.singletonList(alertTypeDetail));

        alertTypeService.reconfigure();

        assertTrue(alertTypeService.getAll().contains(alertTypeDetail));
        verify(alertTypeRepository, times(1)).findAll();
    }
}