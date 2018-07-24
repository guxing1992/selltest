package com.itjava.sell.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class ProductVO implements Serializable {
    private static final long serialVersionUID = 8314911710254643121L;
    @JsonProperty("name")
    private String categoryName;
    @JsonProperty("type")
    private Integer categoryType;
    private List<ProductInfoVO> foods;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(Integer categoryType) {
        this.categoryType = categoryType;
    }

    public List<ProductInfoVO> getFoods() {
        return foods;
    }

    public void setFoods(List<ProductInfoVO> foods) {
        this.foods = foods;
    }
}
