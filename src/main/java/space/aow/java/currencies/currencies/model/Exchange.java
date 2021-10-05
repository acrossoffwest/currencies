package space.aow.java.currencies.currencies.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Exchange {
    private Long id;
    private double amount;
    private Timestamp createdAt;
    private Long currencyId;

    public Exchange(double amount, Timestamp createdAt) {
        setAmount(amount);
        setCreatedAt(createdAt);
    }
}
