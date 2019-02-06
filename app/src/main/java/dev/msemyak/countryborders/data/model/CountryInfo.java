package dev.msemyak.countryborders.data.model;

import java.util.List;

public class CountryInfo {

    private String name;
    private String code;
    private List<String> borders;

    public CountryInfo(Country country) {
        this.name = country.getName();
        this.code = country.getAlpha3Code();
        this.borders = country.getBorders();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<String> getBorders() {
        return borders;
    }

    public void setBorders(List<String> borders) {
        this.borders = borders;
    }

    @Override
    public String toString() {
        return "CountryInfo{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", borders=" + borders +
                '}';
    }
}
