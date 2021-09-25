package space.aow.java.currencies.currencies.model;

import lombok.Data;

@Data
public class Currency {
    private String code;
    private String name;
    private double exchange;

    public Currency(String code, String name, double exchange) {
        setCode(code);
        setName(name);
        setExchange(exchange);
    }
}
