package com.careerdevs.stakeit.controllers;

import com.careerdevs.stakeit.Repositories.AssetRepository;
import com.careerdevs.stakeit.models.Asset;
import com.careerdevs.stakeit.utils.ApiErrorHandling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
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
        try {
            String apiKey = env.getProperty("ALPHA_VANTAGE_KEY");
            String url = "https://www.alphavantage.co/query?function=OVERVIEW&symbol=" + symbol + "&apikey=" + apiKey;
            Asset requestData = restTemplate.getForObject(url, Asset.class);

            Asset savedOverview = assetRepository.save(requestData);

            return new ResponseEntity<>(savedOverview, HttpStatus.CREATED);
        }catch (HttpClientErrorException e){
            return ApiErrorHandling.customApiError(e.getMessage(), e.getStatusCode().value());

        } catch (Exception e){
            return ApiErrorHandling.genericApiError(e);
        }

    }

    @GetMapping("/")
    public ResponseEntity<?> getAllAssets(){
        try {
            List<Asset> assets = assetRepository.findAll();
            return new ResponseEntity<>(assets, HttpStatus.OK);
        }catch (HttpClientErrorException e){
            return ApiErrorHandling.customApiError(e.getMessage(), e.getStatusCode().value());

        } catch (Exception e){
            return ApiErrorHandling.genericApiError(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneAsset(@PathVariable Long id){
        try {
            Optional<Asset> asset = assetRepository.findById(id);
            return new ResponseEntity<>(asset, HttpStatus.OK);
        }catch (HttpClientErrorException e){
            return ApiErrorHandling.customApiError(e.getMessage(), e.getStatusCode().value());

        } catch (Exception e){
            return ApiErrorHandling.genericApiError(e);
        }
    }

    @GetMapping("/symbol/{symbol}")
    public ResponseEntity<?> getAssetByName(@PathVariable String symbol){
        try {
            Asset asset = assetRepository.findBySymbol(symbol);
            return new ResponseEntity<>(asset, HttpStatus.OK);
        }catch (HttpClientErrorException e){
            return ApiErrorHandling.customApiError(e.getMessage(), e.getStatusCode().value());

        } catch (Exception e){
            return ApiErrorHandling.genericApiError(e);
        }
    }


}
