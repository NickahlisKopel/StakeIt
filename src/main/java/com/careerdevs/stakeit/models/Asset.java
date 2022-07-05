package com.careerdevs.stakeit.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("Symbol")
    private String symbol;

    @JsonProperty("AssetType")
    @Column(name = "asset_type")
    private String assetType;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Currency")
    private String currency;

    @JsonProperty("Country")
    private String country;


    public Asset(Long id, String symbol, String assetType, String name, String currency, String country) {
        this.id = id;
        this.symbol = symbol;
        this.assetType = assetType;
        this.name = name;
        this.currency = currency;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
