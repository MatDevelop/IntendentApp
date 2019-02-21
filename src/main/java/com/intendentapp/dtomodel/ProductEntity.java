package com.intendentapp.dtomodel;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity(name="products")
public class ProductEntity implements Serializable{

    private String name;
    @Id
    private String number;
    private String unit;
    private Double unitprice;

    public ProductEntity(){}

    public ProductEntity(String name, String number, String unit, Double unitprice) {
        this.name = name;
        this.number = number;
        this.unit = unit;
        this.unitprice = unitprice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(Double unitprice) {
        this.unitprice = unitprice;
    }
}
