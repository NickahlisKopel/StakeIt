package com.careerdevs.stakeit.Repositories;

import com.careerdevs.stakeit.models.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetRepository extends JpaRepository<Asset, Long> {
    Asset findBySymbol(String symbol);
}
