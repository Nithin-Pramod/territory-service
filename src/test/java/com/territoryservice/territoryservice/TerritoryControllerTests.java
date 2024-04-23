package com.territoryservice.territoryservice;

import com.territoryservice.territoryservice.controller.TerritoryController;
import com.territoryservice.territoryservice.model.TerritoryNode;
import com.territoryservice.territoryservice.service.TerritoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TerritoryControllerTests {

    @Mock
    private TerritoryService territoryService;

    private TerritoryController territoryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        territoryController = new TerritoryController(territoryService);
    }

    @Test
    void testGetAncestorsFound() {
        // Mock data
        List<String> ancestors = Arrays.asList("India", "Maharashtra");
        when(territoryService.findAncestors(any(), anyString())).thenReturn(ancestors);

        // Call the controller method
        ResponseEntity<Map<String, List<String>>> response = territoryController.getAncestors("Maharashtra");

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ancestors, response.getBody().get("ancestors"));
        verify(territoryService, times(1)).findAncestors(any(), eq("Maharashtra"));
    }

    @Test
    void testGetAncestorsNotFound() {
        // Mock data
        when(territoryService.findAncestors(any(), anyString())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<Map<String, List<String>>> response = territoryController.getAncestors("NonExistent");

        // Verify the response
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Not found", response.getBody().get("message").get(0));
        verify(territoryService, times(1)).findAncestors(any(), eq("NonExistent"));
    }

    @Test
    void testGetAncestorsNoAncestorsFound() {
        // Mock data
        List<String> ancestors = Collections.singletonList("No ancestors found");
        when(territoryService.findAncestors(any(), anyString())).thenReturn(ancestors);

        // Call the controller method
        ResponseEntity<Map<String, List<String>>> response = territoryController.getAncestors("NonExistent");

        // Verify the response
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("No ancestors found", response.getBody().get("message").get(0));
        verify(territoryService, times(1)).findAncestors(any(), eq("NonExistent"));
    }
}
