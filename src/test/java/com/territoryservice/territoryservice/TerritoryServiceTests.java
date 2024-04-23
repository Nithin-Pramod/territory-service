package com.territoryservice.territoryservice;

import com.territoryservice.territoryservice.entity.Territory;
import com.territoryservice.territoryservice.model.TerritoryNode;
import com.territoryservice.territoryservice.service.TerritoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TerritoryServiceTests {
    private TerritoryNode rootNode;
    private TerritoryService territoryService;

    @BeforeEach
    public void setUp() throws IOException {
        rootNode = createTestTerritoryTree();
        territoryService = new TerritoryService();
    }

    @Test
    public void testFindAncestors_EmptyTerritoryList() {
        List<Territory> emptyTerritoryList = Collections.emptyList();
        TerritoryNode emptyRootNode = territoryService.convertToTree(emptyTerritoryList);
        List<String> ancestors = territoryService.findAncestors(emptyRootNode, "Chennai");
        assertEquals(Collections.emptyList(), ancestors);
    }

    @Test
    public void testFindAncestors_SingleTerritory() {
        Territory singleTerritory = createTerritory(1, "India", 0);
        TerritoryNode singleRootNode = new TerritoryNode(singleTerritory);
        List<String> ancestors = territoryService.findAncestors(singleRootNode, "India");
        assertEquals(Collections.singletonList("No ancestors found"), ancestors);
    }

    @Test
    public void testFindAncestors_NullTerritoryList() {
        List<String> ancestors = territoryService.findAncestors(null, "Chennai");
        assertEquals(Collections.emptyList(), ancestors);
    }

    @Test
    public void testFindAncestors_NullRootNode() {
        List<String> ancestors = territoryService.findAncestors(null, "Chennai");
        assertEquals(Collections.emptyList(), ancestors);
    }

    @Test
    public void testFindAncestors_NullInput() {
        List<String> ancestors = territoryService.findAncestors(rootNode, null);
        assertEquals(Collections.emptyList(), ancestors);
    }

    @Test
    public void testFindAncestors_InputNotInTree() {
        List<String> ancestors = territoryService.findAncestors(rootNode, "InvalidTerritory");
        assertEquals(Collections.emptyList(), ancestors);
    }

    @Test
    public void testFindAncestors_RootNodeIsInput() {
        List<String> ancestors = territoryService.findAncestors(rootNode, "India");
        assertEquals(Collections.singletonList("No ancestors found"), ancestors);
    }

    @Test
    public void testFindAncestors_NoAncestorsFound() {
        List<String> ancestors = territoryService.findAncestors(rootNode, "Chennai");
        assertEquals(Arrays.asList("Tamil Nadu", "India"), ancestors);
    }

    private TerritoryNode createTestTerritoryTree() {
        Territory india = createTerritory(1, "India", 0);
        Territory tamilNadu = createTerritory(2, "Tamil Nadu", 1);
        Territory chennai = createTerritory(3, "Chennai", 2);

        TerritoryNode indiaNode = new TerritoryNode(india);
        TerritoryNode tamilNaduNode = new TerritoryNode(tamilNadu);
        TerritoryNode chennaiNode = new TerritoryNode(chennai);

        indiaNode.addChild(tamilNaduNode);
        tamilNaduNode.addChild(chennaiNode);

        return indiaNode;
    }

    private Territory createTerritory(int id, String name, int parentId) {
        Territory territory = new Territory();
        territory.setId(id);
        territory.setName(name);
        territory.setParentId(parentId);
        return territory;
    }
}
