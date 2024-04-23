package com.territoryservice.territoryservice.model;

import com.territoryservice.territoryservice.entity.Territory;

import java.util.List;

public class Territories {
    private List<Territory> territories;

    public List<Territory> getTerritories() {
        return territories;
    }

    public void setTerritories(List<Territory> territories) {
        this.territories = territories;
    }
}
