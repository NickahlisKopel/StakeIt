package com.careerdevs.stakeit.controllers;

import com.careerdevs.stakeit.Repositories.AssetRepository;
import com.careerdevs.stakeit.models.Asset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/assets")
@CrossOrigin
public class AssetController {
    @Autowired
    private Environment env;

    @Autowired
    private AssetRepository assetRepository;



    @PostMapping("/{symbol}")
    public ResponseEntity<?> uploadAsset(RestTemplate restTemplate, @PathVariable("symbol") String symbol){
        String apiKey = env.getProperty("ALPHA_VANTAGE_KEY");
        String url = "https://www.alphavantage.co/query?function=OVERVIEW&symbol=" + symbol + "&apikey=" + apiKey;
        Asset requestData = restTemplate.getForObject(url, Asset.class);

        Asset savedOverview = assetRepository.save(requestData);

        return new ResponseEntity<>(savedOverview, HttpStatus.CREATED);

    }

    @GetMapping("/")
    public ResponseEntity<?> getAllAssets(){
        List<Asset> assets = assetRepository.findAll();
        return new ResponseEntity<>(assets, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneAsset(@PathVariable Long id){
        Optional<Asset> asset = assetRepository.findById(id);
        return new ResponseEntity<>(asset, HttpStatus.OK);
    }

    @GetMapping("/symbol/{symbol}")
    public ResponseEntity<?> getAssetByName(@PathVariable String symbol){
        Asset asset = assetRepository.findBySymbol(symbol);
        return new ResponseEntity<>(asset, HttpStatus.OK);
    }


}
