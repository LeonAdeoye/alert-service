package com.leon.controllers;

import com.leon.models.AlertTypeDetail;
import com.leon.services.AlertTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Collections;
import java.util.UUID;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AlertTypeController.class)
class AlertTypeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlertTypeService alertTypeService;

    private AlertTypeDetail alertTypeDetail;

    @BeforeEach
    void setUp() {
        alertTypeDetail = new AlertTypeDetail();
        alertTypeDetail.setId(UUID.randomUUID());
        alertTypeDetail.setExplanation("Test Explanation");
    }

    @Test
    void testReconfigure() throws Exception {
        mockMvc.perform(get("/alert/type/reconfigure"))
                .andExpect(status().isNoContent());
        verify(alertTypeService, times(1)).reconfigure();
    }

    @Test
    void testGetAll() throws Exception {
        when(alertTypeService.getAll()).thenReturn(Collections.singletonList(alertTypeDetail));

        mockMvc.perform(get("/alert/type")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].explanation").value("Test Explanation"));
        verify(alertTypeService, times(1)).getAll();
    }

    @Test
    void testCreateAlertTypeValid() throws Exception {
        when(alertTypeService.createAlertType(any(AlertTypeDetail.class))).thenReturn(alertTypeDetail);

        mockMvc.perform(post("/alert/type")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":\"" + alertTypeDetail.getId() + "\",\"explanation\":\"Test Explanation\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.explanation").value("Test Explanation"));
        verify(alertTypeService, times(1)).createAlertType(any(AlertTypeDetail.class));
    }

    @Test
    void testCreateAlertTypeInvalid() throws Exception {
        mockMvc.perform(post("/alert/type")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
        verify(alertTypeService, never()).createAlertType(any(AlertTypeDetail.class));
    }

    @Test
    void testDeleteAlertTypeValid() throws Exception {
        doNothing().when(alertTypeService).deleteAlertType(anyString());

        mockMvc.perform(delete("/alert/type/" + alertTypeDetail.getId()))
                .andExpect(status().isNoContent());
        verify(alertTypeService, times(1)).deleteAlertType(alertTypeDetail.getId().toString());
    }

    @Test
    void testUpdateAlertTypeValid() throws Exception {
        when(alertTypeService.updateAlertType(any(AlertTypeDetail.class))).thenReturn(alertTypeDetail);

        mockMvc.perform(put("/alert/type")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":\"" + alertTypeDetail.getId() + "\",\"explanation\":\"Updated Explanation\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.explanation").value("Test Explanation"));
        verify(alertTypeService, times(1)).updateAlertType(any(AlertTypeDetail.class));
    }

    @Test
    void testUpdateAlertTypeInvalid() throws Exception {
        mockMvc.perform(put("/alert/type")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
        verify(alertTypeService, never()).updateAlertType(any(AlertTypeDetail.class));
    }
}
