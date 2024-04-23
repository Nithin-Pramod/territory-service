package com.territoryservice.territoryservice.configuration;

import com.territoryservice.territoryservice.entity.Territory;
import com.territoryservice.territoryservice.model.TerritoryNode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {


    @Bean
    public TerritoryNode territoryNode(Territory territory) {
        return new TerritoryNode(territory);
    }

    @Bean
    public Territory territory() {
        return new Territory();
    }


}
