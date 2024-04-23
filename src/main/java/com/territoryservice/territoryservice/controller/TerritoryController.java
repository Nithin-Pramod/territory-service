package com.territoryservice.territoryservice.controller;

import com.territoryservice.territoryservice.model.TerritoryNode;
import com.territoryservice.territoryservice.service.TerritoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TerritoryController {
    private final TerritoryService territoryService;
    private final TerritoryNode rootNode;

    @Autowired
    public TerritoryController(TerritoryService territoryService) {
        this.territoryService = territoryService;
        this.rootNode = territoryService.getRootNode();
    }
    @GetMapping("/ancestors/{territoryName}")
    public ResponseEntity<Map<String, List<String>>> getAncestors(@PathVariable String territoryName) {
        // Call the TerritoryService to find ancestors
        List<String> ancestors = territoryService.findAncestors(rootNode, territoryName);
        System.out.println(ancestors);
        if (ancestors.isEmpty()) {
            // If no ancestors found, return 404 Not Found
            Map<String, List<String>> response = new HashMap<>();
            response.put("message", Collections.singletonList("Not found"));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        if (ancestors.contains("No ancestors found")) {
            // If no ancestors found, return 404 Not Found
            Map<String, List<String>> response = new HashMap<>();
            response.put("message", Collections.singletonList("No ancestors found"));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }


        // If ancestors found, return 200 OK with ancestors in the response body
        Map<String, List<String>> response = new HashMap<>();
        response.put("ancestors", ancestors);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
