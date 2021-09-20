package space.aow.java.currencies.currencies.Services.Parser.Models;

public class Currency {
    private String code;
    private String name;
    private double exchange;

    public Currency(String code, String name, double exchange) {
        setCode(code);
        setName(name);
        setExchange(exchange);
    }

    public Currency setCode(String code) {
        this.code = code;
        return this;
    }

    public String getCode() {
        return code;
    }

    public Currency setName(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
    }

    public Currency setExchange(double exchange) {
        this.exchange = exchange;
        return this;
    }

    public double getExchange() {
        return exchange;
    }
}
