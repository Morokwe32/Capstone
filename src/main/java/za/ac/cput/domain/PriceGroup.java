package za.ac.cput.domain;

import java.io.Serializable;
import java.util.Arrays;

public enum PriceGroup implements Serializable
{
    ECONOMY(550.00),
    STANDARD(650.00),
    LUXURY(800.00),
    PREMIUM(3000.00),
    EXOTIC(20000.00),
    SPECIAL(450.00),
    OTHER(700.00),
    NONE(0.00);

    private double bookPrice;

    PriceGroup(double bookPrice) {
        this.bookPrice = bookPrice;
    }

    public static PriceGroup fromString(String value)
    {
        return Arrays.stream(PriceGroup.values())
                .filter(group -> group.name().equalsIgnoreCase(value))
                .findFirst()
                .orElse(null);
    }

    public double getBookPrice() {
        return bookPrice;
    }
}
