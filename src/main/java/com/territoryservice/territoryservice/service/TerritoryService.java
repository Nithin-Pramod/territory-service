package com.territoryservice.territoryservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.territoryservice.territoryservice.model.Territories;
import com.territoryservice.territoryservice.entity.Territory;
import com.territoryservice.territoryservice.model.TerritoryNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class TerritoryService {

    private final TerritoryNode rootNode;

    @Autowired
    public TerritoryService() throws IOException {
        // Read JSON data and convert to list of Territory objects
        List<Territory> territories = readJSON();

        // Convert list of Territory objects to tree structure
        this.rootNode = convertToTree(territories);
    }

    public TerritoryNode getRootNode() {
        return rootNode;
    }


    public List<Territory> readJSON() throws IOException {
        // Read the JSON file
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("territories.json");

        // Use Jackson ObjectMapper to map JSON to Java objects
        ObjectMapper objectMapper = new ObjectMapper();
        Territories territories = objectMapper.readValue(inputStream, Territories.class);

        return territories.getTerritories();
    }

    public TerritoryNode convertToTree(List<Territory> territories) {
        // Create a map to store territories by their ID for easy lookup
        Map<Integer, TerritoryNode> territoryMap = new HashMap<>();

        // Create TerritoryNode instances for each territory
        for (Territory territory : territories) {
            territoryMap.put(territory.getId(), new TerritoryNode(territory));
        }

        // Iterate over territories to build the tree structure
        TerritoryNode rootNode = null;
        for (Territory territory : territories) {
            TerritoryNode node = territoryMap.get(territory.getId());
            if (territory.getParentId() == 0) {
                // This territory is the root node
                rootNode = node;
            } else {
                // Find parent node and add this node as its child
                TerritoryNode parent = territoryMap.get(territory.getParentId());
                parent.addChild(node);
            }
        }
        return rootNode;
    }

    public List<String> findAncestors(TerritoryNode root, String input) {
        // Create a list to store ancestors
        List<String> ancestors = new ArrayList<>();

        // Find the territory node corresponding to the input territory name
        TerritoryNode targetNode = findNodeByName(root, input);

        // If the target node is found
        if (targetNode != null) {
            // If the target node is not the root node
            if (targetNode.getTerritory().getName().equals(root.getTerritory().getName())) {
                ancestors.add("No ancestors found");
                return ancestors;
            }

            // If the target node is not the root node
            TerritoryNode currentNode = targetNode;
            while (currentNode.getParent() != null) {
                currentNode = currentNode.getParent();
                ancestors.add(currentNode.getTerritory().getName());
            }
        }

        return ancestors;
    }


    // Helper method to find a node by its territory name
    public TerritoryNode findNodeByName(TerritoryNode root, String name) {
        if (name == null) {
            return null;
        }
        if (root == null || root.getTerritory() == null || root.getTerritory().getName() == null) {
            return null;
        }
        if (root.getTerritory().getName().equals(name)) {
            return root;
        }
        for (TerritoryNode child : root.getChildren()) {
            TerritoryNode foundNode = findNodeByName(child, name);
            if (foundNode != null) {
                return foundNode;
            }
        }
        return null;
    }


}
