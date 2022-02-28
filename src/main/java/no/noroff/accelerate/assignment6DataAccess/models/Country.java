package no.noroff.accelerate.assignment6DataAccess.models;

public class Country {

    private String country;
    private String numbers;

    public Country(String country, String numbers) {
        this.country = country;
        this.numbers = numbers;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }
}
