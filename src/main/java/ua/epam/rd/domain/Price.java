package ua.epam.rd.domain;

import org.springframework.stereotype.Component;


@Component
public class Price {
    private Double price;
    private Double discount;

    public Price(Double price, Double discount) {
        this.price = price;
        this.discount = discount;
    }

    public Price() {
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Price: " + price + ", Discount: " + discount;
    }
}
