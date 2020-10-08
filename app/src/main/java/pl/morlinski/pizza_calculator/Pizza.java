package pl.morlinski.pizza_calculator;

import java.util.Optional;

public class Pizza {
    private int diameter;
    private int quantity;
    private Optional<Integer> price;

    public Pizza() {
        price = Optional.empty();
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(int price) {
        this.price = Optional.of(price);
    }

    public boolean isSet() {
        return price.isPresent();
    }

    public double area() {
        return Math.PI * Math.pow(diameter / 2.0, 2) * quantity;
    }

    public double pricePer_1cm_2() {
        return price.get() / area();
    }
}
