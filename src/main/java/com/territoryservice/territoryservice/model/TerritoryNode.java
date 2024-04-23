package com.territoryservice.territoryservice.model;

import com.territoryservice.territoryservice.entity.Territory;
import java.util.ArrayList;
import java.util.List;

public class TerritoryNode {
    private Territory territory;
    private TerritoryNode parent; // Reference to parent node
    private List<TerritoryNode> children;

    public TerritoryNode(Territory territory) {
        this.territory = territory;
        this.children = new ArrayList<>();
    }

    public Territory getTerritory() {
        return territory;
    }

    public void setTerritory(Territory territory) {
        this.territory = territory;
    }

    public TerritoryNode getParent() {
        return parent;
    }

    public void setParent(TerritoryNode parent) {
        this.parent = parent;
    }

    public List<TerritoryNode> getChildren() {
        return children;
    }

    public void setChildren(List<TerritoryNode> children) {
        this.children = children;
    }

    public void addChild(TerritoryNode child) {
        child.setParent(this); // Set the parent of the child node
        this.children.add(child);
    }
}
